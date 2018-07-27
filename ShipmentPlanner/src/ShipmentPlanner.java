import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;



public class ShipmentPlanner {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, FileNotFoundException {
		ArrayList<Node> allNodes = new ArrayList<Node>();
		ArrayList<Edge> allEdges = new ArrayList<Edge>();
		PriorityQueue<State> allStates  = new PriorityQueue<State>();
		State startState= null;
		String line;
		String subline;
		String[] arguments;
		
		
		Scanner scanner = new Scanner(new File(args[0]));
		try {
			
			boolean firstTime = true;
			while(scanner.hasNextLine()) {
				line = scanner.nextLine();
				if(line.contains("Refuelling")) {
					
					subline = line.substring(11);
					arguments = subline.split(" ");
					//System.out.println(" "+arguments[0]+" "+arguments[1]);
					
					allNodes.add(new Node(arguments[1],Integer.parseInt(arguments[0])));
					
				}
				else if(line.contains("Time")){
					subline = line.substring(5);
					arguments = subline.split(" ");	
					allEdges.add(new Edge(nodeFinder(allNodes,arguments[1]),
							nodeFinder(allNodes,arguments[2]),
							Integer.parseInt(arguments[0])));
					
					allEdges.add(new Edge(nodeFinder(allNodes,arguments[2]),
							nodeFinder(allNodes,arguments[1]),
							Integer.parseInt(arguments[0])));
					
					
				}
				else if(line.contains("Shipment")) {
					//initialize the startState
					if(firstTime ==true) {
						startState = new State(nodeFinder(allNodes,"Sydney"));
						firstTime = false;
					}
					subline = line.substring(9);
					arguments = subline.split(" ");
					Node tripFromNode = nodeFinder(allNodes, arguments[0]);
					Node tripToNode = nodeFinder(allNodes, arguments[1]);
					
					Edge trip = edgeFinder(allEdges,tripFromNode,tripToNode);
					startState.tripRemain.add(trip);
				}
				
			}
		}
	      finally
	      {
	          if (scanner != null) scanner.close();
	      }
		
		//check for the initialization
		// printAllEdges(allEdges);
		 //printAllNodes(allNodes);
			for(Node node : allNodes) {
				node.edges = allEdges;
			}
		
		//algorithm starts here
		startState.startScoreCalculate();
		
		allStates.add(startState);
		State nextState = null;
				
		int n=1;
		//boolean finish=false;
		while (true) {
			State currState = allStates.remove();
			if(currState.tripRemain.isEmpty()) {
				break;
			}
			for(Edge nextTrip : currState.tripRemain) {
				
				
				nextState = currState.goToNext(nextTrip);
				
				
				n++;
				
				if(nextState.tripRemain.isEmpty()) {
					
					//finish = true;
					
				}
				//nextState.printState();
				allStates.add(nextState);
				
				}
		}
		System.out.println(n+" nodes expanded");
		//nextState.printState();
		nextState.printFinal();
		
		
		
		
		
		
		
	}
	public static Node nodeFinder(ArrayList<Node> allNodes, String name) {
		for(Node node : allNodes) {
			if(name.equals(node.getName()))
			{
				return node;
			}
		}
		System.out.println("EEEEEEEEEEErrrrrorororororor!!in nodeFinder");
		System.out.println("Cant find "+name);
		return null;
	}
	public static Edge edgeFinder(ArrayList<Edge> allEdges, Node nodeFrom, Node nodeTo) {
		for (Edge edge : allEdges) {
			if(edge.getNodeFrom().isequal(nodeFrom)&&edge.getNodeTo().isequal(nodeTo)) {
				return edge;
			}
		}
		System.out.println("EEEEEEEEEEErrrrrorororororor!! in edgeFinder");
		System.out.println("Cant find from "+ nodeFrom.getName()+" " +nodeTo.getName());
		return null;
		
	}
	static void printAllEdges(ArrayList<Edge>edges) {
		System.out.println("Printing all edges******************");
		for (Edge edge : edges) {
			System.out.println(edge.getNodeFrom().getName()+" "+
		edge.getTimeSpend()+" "+edge.getNodeTo().getName());
		}
		
		System.out.println("***********************************");
	}
	static void printAllNodes(ArrayList<Node>nodes) {
		
		System.out.println("Printing all nodes******************");
		for (Node node : nodes) {
			System.out.println(node.getName()+" "+node.getFuelTime());
		}
		
		System.out.println("***********************************");
	}
	
}

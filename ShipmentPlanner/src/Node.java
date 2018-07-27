import java.util.ArrayList;


public class Node{

	private String name;
	private int fuelTime;
	public ArrayList<Edge> edges;
	
	
	
	public Node(String name, int fuelTime) {
		this.name = name;
		this.fuelTime = fuelTime;
		this.edges = new ArrayList<Edge>();
	}
	public boolean isequal(Node node) {
		//System.out.println(this.name+" "+node.name);
		return (this.name.equals(node.name));
	}
	public String getName() {
		return name;
	}

	public int getFuelTime() {
		return fuelTime;
	}

	public void addEdge(Edge e){
		this.edges.add(e);
	}
	public int getTripCost(Node toNode) {
		//System.out.println("comparing "+ this.name +" and "+toNode.name);
		if(this.isequal(toNode)) {
			//System.out.println("same node ,return 0");
			return 0;
		}
		for (Edge edge : edges) {
			
	
			if(edge.getNodeFrom().isequal(this) &&
					edge.getNodeTo().isequal(toNode)) {
				//System.out.println("I want to get here!!!!!");
				return edge.getTripCost();
			}
		}
		System.out.println("Errrorrrrrrrr!!!! in get TripCost ");
		System.out.println("Cant get: ");
		printNode();
		toNode.printNode();
		
		return 0;
	}


	//for debug
	public void printNode() {
		System.out.println("printing Node:");
		System.out.println("name: "+ this.name);
		System.out.println("fuelTime: "+this.fuelTime+"\n");
		
	}
}

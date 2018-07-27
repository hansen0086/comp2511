import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Test {
	//change this function to main() to debug the  A* algorithm
	public static void test(String[] args) throws InstantiationException, IllegalAccessException {
		
		ArrayList<Node> allNodes = new ArrayList<Node>();
		
		Node c1 = new Node("Sydney",3);
		Node c2 = new Node("Beijing",1);
		Node c3 = new Node("Shanghai",2);
		Node c4 = new Node("Guangzhou",4);
		
		
		allNodes.add(c1);
		allNodes.add(c2);
		allNodes.add(c3);
		allNodes.add(c4);
		
		//sydney to Beijing
		Edge r1 = new Edge(c1,c2,2);
		Edge r2 = new Edge(c2,c1,2);
		//Sydney to Guangzhou
		
		Edge r3 = new Edge(c1,c3,5);
		Edge r4 = new Edge(c3,c1,5);

		//Sydney to Shanghai
		Edge r5 = new Edge(c1,c4,3);
		Edge r6 = new Edge(c4,c1,3);
		//Beijing to SHanghai
		Edge r7 = new Edge(c2,c3,4);
		Edge r8 = new Edge(c3,c2,4);
		//Beijing to Guangzhou
		Edge r9 = new Edge(c2,c4,2);
		Edge r10 = new Edge(c4,c2,2);
		//Shanghai to Guangzhou
		Edge r11 = new Edge(c3,c4,1);
		Edge r12 = new Edge(c4,c3,1);
		
		ArrayList<Edge> allEdges = new ArrayList<Edge>();
		
		allEdges.addAll(Arrays.asList(r1,r2,r3,r4,r5,r6,r7
				,r8,r9,r10,r11,r12));
		
		for(Node node : allNodes) {
			node.edges = allEdges;
		}
		
		
		
		
		
		
		
		
		//generate new State start in Sydney
		State startState = new State(c1);
		
		//generate trip from sydney to beijing, from beijing to shanghai
		//and from beijing to Guangzhou
		startState.tripRemain.add(r1);
		startState.tripRemain.add(r7);
		startState.tripRemain.add(r9);
		startState.startScoreCalculate();
		
		
		//Start to loop through the algorithm for the best solution
		PriorityQueue<State> allStates  = new PriorityQueue<State>();
		allStates.add(startState);
		//Warning: there is bug here!!! Not fixed yet 
		//might not produce the optimal solution
		int n=1;
		boolean finish=false;
		while (finish==false) {
			State currState = allStates.remove();
			for(Edge nextTrip : currState.tripRemain) {
				State nextState = currState.goToNext(nextTrip);
				n++;
				nextState.printState();
				if(nextState.tripRemain.isEmpty()) {
					finish = true;
					break;
				}
				allStates.add(nextState);
				
				}
		}
		
		
		
	}
	
	
}

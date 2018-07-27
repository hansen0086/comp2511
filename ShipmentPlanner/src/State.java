import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class State implements Comparable<State>{
	
	private Node currNode;
	ArrayList<Edge> tripFinished;
	ArrayList <Edge> tripRemain;
	
	private int fScore;
	private int gScore;
	private int hScore;
	
	/*
	 * Constructor for State, this is called in the initial state and also 
	 * the goto() function where new State is deep cloned and returned.
	 * 
	 * this constructor should only be called once which
	 * is in the start of the search
	 */
	public State(Node currNode) {
		//System.out.println("State constructor is called !!!\n this should only appear once!");
		this.currNode = currNode;
		this.tripFinished = new ArrayList<Edge>();
		this.tripRemain = new ArrayList <Edge>();
		
		this.gScore = 0;
		this.hScore = hScoreCalculate();
		this.fScore = gScore + hScore;
	}
	
	public void startScoreCalculate() {
		this.gScore = 0;
		this.hScore = hScoreCalculate();
		this.fScore = gScore + hScore;
	}
	
	/*
	 * a clone by hand to deep clone the currState
	 */
	/*public State clone(){
		//System.out.println("I was here");
		State newState = null;
		try {
			newState = State.Constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newState.currNode = this.currNode;
		for(Edge trip : this.tripFinished) {newState.tripFinished.add(trip);}
		for(Edge trip : this.tripRemain) {newState.tripRemain.add(trip);}
		newState.fScore = this.fScore;
		newState.gScore = this.gScore;
		newState.hScore = this.hScore;
		return newState;
		
		
	}
	*/
	/*
	 * this function is used to generate the next state of the 
	 * total trip where dest is the next next country. It always
	 */
	public State goToNext (Edge nextTrip) throws InstantiationException, IllegalAccessException {
		if(tripRemain.isEmpty()) {
			System.out.println("finished the trip");
			return null;
		}
		else {
			//pop out the next trip
			
			
			//make as deep copy of the next State
			State nextState = new State(this.currNode);
			//need to set up the nextState
			
			//set up the next currNode
			nextState.currNode = nextTrip.getNodeTo();
			//copy the finished and tripRemain arrays
			for(Edge trip : this.tripFinished) {nextState.tripFinished.add(trip);}
			for(Edge trip : this.tripRemain) {nextState.tripRemain.add(trip);}
			
			
			
			//set up the next tripFished
			nextState.tripFinished.add(nextTrip);
			
			
			//set up the next tripRemain,
			//if we can finish the work directly, 
			//clear this trip from tripRemain
			
			nextState.tripRemain.remove(nextTrip);
			
				
			//not need to change since it already been pulled
			
			//set up gScore, hScore, fScore of the nextState
			
			// new gSocre = old gSocore    + the time need from current node to task's start node + nextTrip's cost
			 nextState.gScore =this.gScore + this.currNode.getTripCost(nextTrip.getNodeFrom()) +nextTrip.getTripCost();
			 //System.out.println(this.gScore+" "+ this.currNode.getTripCost(nextState.currNode) + " "+nextTrip.getTripCost());
			 nextState.hScore = nextState.hScoreCalculate();
			 nextState.fScore = nextState.gScore + nextState.hScore;
			
			return nextState;
			
		}
		
	}
	
	
	/*
	 * Heuristic function here.
	 * Generate hScore only using the information in tripRemain
	 */
	public int hScoreCalculate(){
		int estimateTime = 0;
		//Get the total time of the trips remains where tripCost is 
		//just the fuelTime + travelTime in each trip
		for(Edge trip : tripRemain) {
			
			estimateTime += trip.getTripCost();
		}
		//System.out.println(estimateTime);
		return estimateTime;
	}
	
	
	public void printState() {
		System.out.println("------------------------");
		System.out.println("currNode is "+ this.currNode.getName());
		System.out.println("\nI have finished:");
		for(Edge edge : tripFinished) {
			System.out.println("ship: "+ edge.getNodeFrom().getName()+ " to: "+edge.getNodeTo().getName());
			
		}
		System.out.println("\nI still need to do: ");
		for(Edge edge : tripRemain)
		{
			System.out.println("ship: "+ edge.getNodeFrom().getName()+ " to: "+edge.getNodeTo().getName());
		}
		System.out.println("\n");
		System.out.println("f(" + this.fScore + ") =g("+this.gScore+")+h("+this.hScore+")");
		System.out.println("------------------------\n");
	}
	public int compareTo(State state) {
		if(fScore > state.fScore) return 1;
		else return -1;
				
	}
	
	public void printFinal() {
		System.out.println("cost = "+fScore);
		Node laststop =null;
		//Print the first trip
		//if the first trip not Sydney
		int tripCounter =0;
	
		if(!this.tripFinished.get(0).getNodeFrom().getName().equals("Sydney")) {
			System.out.println("ship Sydney to " + this.tripFinished.get(tripCounter).getNodeFrom().getName());
			
		}
		
		for(Edge trip : this.tripFinished)
		{
			
			
			
			//check if the next start is the laststop, skip check if this is first
			if(tripFinished.indexOf(trip)==0) {
				laststop = trip.getNodeFrom(); 
			}
			//if not, print sth
			else if(!trip.getNodeFrom().getName().equals(laststop.getName())){
				//System.out.println(trip.getNodeFrom().getName() +" not equal " +laststop.getName());
				
				System.out.println("Ship "+ laststop.getName()+" to "+trip.getNodeFrom().getName());
			}
			
			//print the trip
			System.out.println("Ship "+ trip.getNodeFrom().getName()+ " to "+trip.getNodeTo().getName());
			//reconfigure laststop
			laststop = trip.getNodeTo();
			
			
		}
		
		
		
		
		
				
	}
	
	
}

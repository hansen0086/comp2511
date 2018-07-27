
public class Edge {
	
	//Edge is the road,from a country to another,
	//it can also be regard as the trip between them
	private Node nodeFrom;
	private Node nodeTo;
	private int timeSpend;
	
	
	public Edge(Node nodeFrom, Node nodeTo, int timeSpend) {
		this.nodeFrom = nodeFrom;
		this.nodeTo = nodeTo;
		this.timeSpend = timeSpend;
		
	}

	public Edge clone() throws CloneNotSupportedException {
		Edge edge = (Edge) super.clone();
		
		return edge;
	}
	public boolean isequal(Edge edge) {
		
		
		
		if(this.nodeFrom.equals(edge.nodeFrom)&&this.nodeTo.equals(edge.nodeTo)) {
		//	System.out.println("same edges detected");
		}
	//	System.out.println(this.nodeFrom.getName()+"!="+edge.getNodeFrom().getName()+"or\n"
		//		+this.nodeTo.getName()+"!="+edge.nodeTo.getName());
		return (this.nodeFrom.equals(edge.nodeFrom)&&this.nodeTo.equals(edge.nodeTo));
	}
	
	public Node getNodeFrom() {
		return nodeFrom;
	}


	public Node getNodeTo() {
		return nodeTo;
	}


	public int getTimeSpend() {
		return timeSpend;
	}


	public int getTripCost() {
		
		
		int tripCost = this.timeSpend + nodeFrom.getFuelTime();
		
		//System.out.println("tripcost "+nodeFrom.getName()+" "+this.getNodeTo().getName()+" "+tripCost );
		return tripCost;
	}
	//for debug
	public void printEdge() {
	
		
		System.out.println("printing Edge");
		System.out.println("nodeFrom: "+ this.nodeFrom.getName());
		System.out.println("nodeTo" + this.nodeTo.getName());
		System.out.println("timeSpend" +this.timeSpend);
		
	}
	
	
	
	
}

package citygenerator.graph;

import java.util.ArrayList;

public abstract class Graph implements Locale{

	private ArrayList<CityNode> nodes = new ArrayList<CityNode>();
	private ArrayList<CityEdge> edges = new ArrayList<CityEdge>();
	private ArrayList<Graph> subGraphs = new ArrayList<Graph>();
	protected CityNode lastNode; //TODO FIXME remove
	protected Point3D size;
	protected Point3D minimumPoint;
	protected String name;
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	/**
	 * Adds a node to the internal list of nodes
	 * @param node	the node to be added
	 */
	public void addNode(CityNode node){
		nodes.add(node);
	}
	
	/**
	 * Handles everything needed to connect two nodes.
	 * <p>
	 * 1) Creates an edge from n1 to n2
	 * 2) Duplicates the given edge from n2 to n1
	 * 3) Adds n2 to n1's adjacent node list
	 * 4) Adds n1 to n2's adjacent node list
	 * @param n1	the first node
	 * @param n2	the second node
	 * @param edge	the edge to connect the two nodes
	 */
	public void addEdge(CityNode n1, CityNode n2, CityEdge edge){
		
		boolean edgeExists = false, reverseExists = false;
		for (CityEdge e : edges){
			CityNode from = e.getFromNode(), to = e.getToNode();
			if(from.equals(n1) && to.equals(n2)){
				edgeExists = true;
			}
			if(from.equals(n2) && to.equals(n1)){
				reverseExists = true;
			}
		}
		if(!edgeExists){
			edges.add(edge);
			n1.addAdjacentNode(n2);
		}

		if(!reverseExists){
			edges.add(edge.getReverse());
			n2.addAdjacentNode(n1);
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		
		int count = 1;
		for(CityNode node : nodes){
			sb.append("\t" + node.getName());
			if(count < nodes.size()){
				sb.append(",\n");
			}
			count++;
		}
		sb.append("\n],\n[\n");
		
		count = 1;
		for(CityEdge edge : edges){
			sb.append("\t{" + edge.getFromNode().getName() + "," + edge.getToNode().getName() + "}");
			if(count < edges.size()){
				sb.append(",\n");
			}
			count++;
		}
		
		sb.append("\n]");
		return sb.toString();
	}
	
	public CityNode getNodeByName(String name){
		for(CityNode node : nodes){
			if(node.getName().equals(name))
				return node;
		}
		return null;
	}

	public ArrayList<CityNode> getNodes() {
		return nodes;
	}
	
	public ArrayList<CityNode> getAllNodes(){
		ArrayList<CityNode> allNodes = new ArrayList<CityNode>();
		allNodes.addAll(nodes);
		for(Graph g : subGraphs){
			allNodes.addAll(g.getAllNodes());
		}
		return allNodes;
	}

	public ArrayList<CityEdge> getEdges() {
		return edges;
	}
	
	public void addSubGraph(Graph g){
		this.subGraphs.add(g);
	}
	
	public ArrayList<Graph> getSubGraphs(){
		return subGraphs;
	}

	public ArrayList<CityEdge> getAllEdges() {
		ArrayList<CityEdge> allEdges = new ArrayList<CityEdge>();
		allEdges.addAll(edges);
		for(Graph g : subGraphs){
			allEdges.addAll(g.getAllEdges());
		}
		return allEdges;
	}
	
	
	public void setLastNode(CityNode cn){
		this.lastNode = cn;
	}
	
	public CityNode getLastNode(){
		return lastNode;
	}
	
	public void setSize(Point3D sizes){
		this.size = sizes;
	}
	
	public Point3D getSize(){
		return size;
	}
	
	public Point3D getMinimumPoint(){
		return minimumPoint;
	}
	
	public void setMinimumPoint(Point3D minPoint3d){
		this.minimumPoint = minPoint3d;
	}

}

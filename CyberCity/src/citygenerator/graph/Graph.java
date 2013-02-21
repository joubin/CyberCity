package citygenerator.graph;

import java.util.ArrayList;

public abstract class Graph implements Locale{

	private ArrayList<CityNode> nodes = new ArrayList<CityNode>();
	private ArrayList<Graph> subGraphs = new ArrayList<Graph>();
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
	 * 1) Creates an edge from n1 to n2 <p>
	 * 2) Duplicates the given edge from n2 to n1 <p>
	 * 3) Adds n2 to n1's adjacent node list <p>
	 * 4) Adds n1 to n2's adjacent node list <p>
	 * @param n1	the first node
	 * @param n2	the second node
	 * @param edge	the edge to connect the two nodes
	 */
	public void addEdge(CityNode n1, CityNode n2, CityEdge edge){
		
		boolean edgeExists = false, reverseExists = false;

        for (CityEdge e : n1.getAdjacentNodes()){
            if(e.getToNode().getName().equals(n2.getName()))
                edgeExists = true;
        }

        for (CityEdge e : n2.getAdjacentNodes()){
            if(e.getToNode().getName().equals(n1.getName()))
                reverseExists = true;
        }

		if(!edgeExists){
			n1.addAdjacentNode(edge);
		}

		if(!reverseExists){
			CityEdge reverse = edge.getReverse();
			n2.addAdjacentNode(reverse);
		}

	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		
//		int count = 1;
//		for(CityNode node : nodes){
//			sb.append("\t" + node.getName());
//			if(count < nodes.size()){
//				sb.append(",\n");
//			}
//			count++;
//		}
//		sb.append("\n],\n[\n");
//
//		count = 1;
//		for(CityEdge edge : edges){
//			sb.append("\t{" + edge.getFromNode().getName() + "," + edge.getToNode().getName() + "}");
//			if(count < edges.size()){
//				sb.append(",\n");
//			}
//			count++;
//		}
		
		sb.append("\n]");
		return sb.toString();
	}
	
//	public CityNode getNodeByName(String name){
//		for(CityNode node : nodes){
//			if(node.getName().equals(name))
//				return node;
//		}
//		return null;
//	}

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
	
	public void addSubGraph(Graph g){
		this.subGraphs.add(g);
	}
	
//	public ArrayList<Graph> getSubGraphs(){
//		return subGraphs;
//	}
	
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

//	@Override
//	public boolean isPointOccupied(Point3D point){
//		boolean outsideX = point.x < minimumPoint.x || point.x > minimumPoint.x + size.x;
//		boolean outsizeZ = point.z < minimumPoint.z || point.z > minimumPoint.z + size.z;
//		
//		if(outsideX && outsizeZ){
//			return false;
//		}
//		return true;
//	}
	
}

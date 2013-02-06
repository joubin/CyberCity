package generator;

import java.awt.Point;
import java.util.ArrayList;

import graph.CityEdge;
import graph.CityNode;
import graph.Graph;
import graph.Point3D;

public class Downtown extends Graph {

	private int maxSize;
	CityNode[][] grid;
	Point.Double[][] points;
	int currentX = 0, currentZ = 0;
	private int root;
	
	public Downtown(String name){
		setName(name);
	}
	
	@Override
	public CityNode setNext(Point3D point) {
		CityNode cn = new Building(name + "-" + getNodes().size(), point);
		
		grid[currentX][currentZ] = cn;
			
		addNode(cn);
		
		updateEdges(cn);
		updatePointers();
		
		return cn;
	}

	@Override
	public CityNode setFirst(Point3D startPoint) {
		
		root = (int) Math.ceil(Math.sqrt(maxSize));

		grid = new CityNode[root][root];
		points = new Point.Double[root][root];
		
		for(int i = 0; i < root; i++){
			for(int j = 0; j < root; j++){
				double x = (double)i / (double)root * size.x + minimumPoint.x;
				double z = (double)j / (double)root * size.z + minimumPoint.z;
				points[i][j] = new Point.Double(x, z);
			}
		}

		return setNext(startPoint);
	}
	
	@Override
	public int getMaxSize() {
		return maxSize;
	}

	@Override
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public Point.Double requestPoint() {
		Point.Double p = points[currentX][currentZ];
		return p;
	}

	private void updateEdges(CityNode cn) {
		Road edge = new Road();
		CityNode toNode = null;
		
		if(currentX >= 0 && currentZ >= 0 && currentX < root && currentZ < root){
			if(currentX - 1 >= 0 && grid[currentX-1][currentZ] != null){
				toNode = grid[currentX-1][currentZ];
			}
			if(currentX + 1 < root && grid[currentX+1][currentZ] != null){
				toNode = grid[currentX+1][currentZ];
			}
			if(currentZ - 1 >= 0 && grid[currentX][currentZ-1] != null){
				toNode = grid[currentX][currentZ-1];
			}
			if(currentZ + 1 < root && grid[currentX][currentZ+1] != null){
				toNode = grid[currentX][currentZ+1];
			} else {
				return;
			}
		}
		edge.setNodes(cn, toNode);
		edge.setLength(cn.getCoordinates().distance(toNode.getCoordinates()));
		addEdge(cn, toNode, edge);
	}

	private void updatePointers() {
		
		if(currentZ < root - 1)
			currentZ++;
		else if(currentZ == root - 1){
			currentZ = 0;
			if(currentX < root - 1)
				currentX++;
			else if(currentX == root - 1){
				currentX = 0;
			}
		}
	}
	
}

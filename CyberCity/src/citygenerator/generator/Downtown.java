package citygenerator.generator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import citygenerator.graph.CityEdge;
import citygenerator.graph.CityNode;
import citygenerator.graph.Graph;
import citygenerator.graph.Point3D;

public class Downtown extends Graph {

	private int maxSize;
	CityNode[][] grid;
	Point.Double[][] points;
	int currentX = 0, currentZ = 0;
	private int root;

	public Downtown(String name) {
		setName(name);
	}

	@Override
	public CityNode setNext(Point3D point) {
		CityNode cn = new Building(name + "_" + getNodes().size(), point);

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

		for (int i = 0; i < root; i++) {
			for (int j = 0; j < root; j++) {
				double x = (double) i / (double) root * size.x + minimumPoint.x;
				double z = (double) j / (double) root * size.z + minimumPoint.z;
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
		
		CityNode toNode = null;

		if (currentX - 1 >= 0 && grid[currentX - 1][currentZ] != null) {
			Road edge = new Road();
			toNode = grid[currentX - 1][currentZ];
			edge.setNodes(cn, toNode);
			edge.setLength(cn.getCoordinates()
					.distance(toNode.getCoordinates()));
			addEdge(cn, toNode, edge);
		}
		if (currentX + 1 < root && grid[currentX + 1][currentZ] != null) {
			Road edge = new Road();
			toNode = grid[currentX + 1][currentZ];
			edge.setNodes(cn, toNode);
			edge.setLength(cn.getCoordinates()
					.distance(toNode.getCoordinates()));
			addEdge(cn, toNode, edge);
		}
		if (currentZ - 1 >= 0 && grid[currentX][currentZ - 1] != null) {
			Road edge = new Road();
			toNode = grid[currentX][currentZ - 1];
			edge.setNodes(cn, toNode);
			edge.setLength(cn.getCoordinates()
					.distance(toNode.getCoordinates()));
			addEdge(cn, toNode, edge);
		}
		if (currentZ + 1 < root && grid[currentX][currentZ + 1] != null) {
			Road edge = new Road();
			toNode = grid[currentX][currentZ + 1];
			edge.setNodes(cn, toNode);
			edge.setLength(cn.getCoordinates()
					.distance(toNode.getCoordinates()));
			addEdge(cn, toNode, edge);
		}

	}

	private void updatePointers() {

		if (currentZ < root - 1)
			currentZ++;
		else if (currentZ == root - 1) {
			currentZ = 0;
			currentX++;
		}
	}

	@Override
	public CityNode getEdgeNode(Point3D otherPoint) {
		boolean isBelowX, isBelowZ;
		Point3D middle = new Point3D(minimumPoint.x + size.x, minimumPoint.y, minimumPoint.z + size.z);
		
		if(otherPoint.x < middle.x)
			isBelowX = true;
		else {
			isBelowX = false;
		}
		
		if(otherPoint.z < middle.z)
			isBelowZ = true;
		else {
			isBelowZ = false;
		}
		
		
		boolean isXVar = Math.abs(otherPoint.z - middle.z) >  Math.abs(otherPoint.x - middle.x) ? true : false;
		int x, z;
		
		x = new Random().nextInt(root);
		
		if(isXVar){
			if(isBelowZ)
				z=0;
			else {
				if(x==root-1)
					z=currentZ-1;
				else
					z = root - 1;
			}
		} else {
			if(isBelowX){
				x=0;
				z = new Random().nextInt(root);
			}
			else{
				x=root-1;
				if(currentZ > 1)
					z = new Random().nextInt(currentZ - 1);
				else
					z = new Random().nextInt(root-1);
			}
			
		}
		
		while(grid[x][z] == null){
			x--;
		};
		return grid[x][z];
	}

}

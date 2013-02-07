package citygenerator.generator;

import java.awt.Point;
import java.util.ArrayList;

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

}

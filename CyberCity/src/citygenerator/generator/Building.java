package citygenerator.generator;

import citygenerator.graph.CityNode;
import citygenerator.graph.Point3D;

import java.awt.Point;
import java.util.ArrayList;

public class Building implements CityNode {

	private ArrayList<CityNode> adjacentNodes = new ArrayList<CityNode>();
	private String name;
	private Point3D coordinates;
	
	public Building () {
		
	}
	
	public Building (String name, Point3D coordinates){
		setName(name);
		setCoordinates(coordinates);
	}

	@Override
	public ArrayList<CityNode> getAdjacentNodes() {
		return adjacentNodes;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Point3D getCoordinates() {
		return coordinates;
	}

	@Override
	public void setCoordinates(Point3D coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public void addAdjacentNode(CityNode to) {
		adjacentNodes.add(to);
	}
}

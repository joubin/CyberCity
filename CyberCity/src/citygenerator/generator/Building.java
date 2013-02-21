package citygenerator.generator;

import citygenerator.graph.CityEdge;
import citygenerator.graph.CityNode;
import citygenerator.graph.Point3D;

import java.util.ArrayList;

public class Building implements CityNode {

	private ArrayList<CityEdge> adjacentNodes = new ArrayList<CityEdge>();
	private String name;
	private Point3D coordinates;

	
	public Building (String name, Point3D coordinates){
		setName(name);
		setCoordinates(coordinates);
	}

	@Override
	public ArrayList<CityEdge> getAdjacentNodes() {
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
	public void addAdjacentNode(CityEdge to) {
        if(!to.getFromNode().equals(this))
            throw new Error(" New edge's origin node should be " + name + ", but is not");

		adjacentNodes.add(to);
	}
	
	public String toString(){
		return getName();
	}
}

package citygenerator.generator;

import citygenerator.graph.CityEdge;
import citygenerator.graph.CityNode;
import citygenerator.graph.Point3D;
import person.Person;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Building implements CityNode {

	private ArrayList<CityEdge> adjacentNodes = new ArrayList<CityEdge>();
	private String name;
	private Point3D coordinates;
    private ArrayList<Person> personList = new ArrayList<Person>();
    //TODO FIXME
    public HashMap<CityNode, CityNode> nextHops = new HashMap<CityNode, CityNode>();

    //Routing Data
    private double distance;
    private CityNode previousNode;
    private boolean isDone;
    private float[] color = {0F, 1F, 0F};


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

    public ArrayList<Person> getPersonList(){
        return personList;
    }

	public String toString(){
		return getName();
	}

    @Override
    public void clearRoutingData(){
        distance = Double.MAX_VALUE;
        previousNode = null;
        isDone = false;
    }

    @Override
    public void registerNextHop(CityNode from, CityNode to){
        nextHops.put(from, to);
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public CityNode getPreviousNode() {
        return previousNode;
    }

    @Override
    public void setPreviousNode(CityNode previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public boolean isDone(){
        return isDone;
    }

    @Override
    public void setDone(boolean b){
        isDone = b;
    }

    @Override
    public CityNode nextHop(CityNode n){
        return nextHops.get(n);
    }

    @Override
    public void setColor(float[] color){
        this.color = color;
    }

    @Override
    public float[] getColor(){
        return color;
    }
}

package citygenerator.graph;

import person.Person;

import java.util.ArrayList;

public interface CityNode {

	public ArrayList<CityEdge> getAdjacentNodes();
	public void addAdjacentNode(CityEdge to);
	
	public String getName();
	
	public void setName(String name);
	
	public Point3D getCoordinates();
	
	public void setCoordinates(Point3D coordinates);

    public void clearRoutingData();

    public CityNode nextHop(CityNode destination);
    public void registerNextHop(CityNode from, CityNode to);

    public boolean isDone();
    public void setDone(boolean b);

    public double getDistance();
    public void setDistance(double distance);

    public CityNode getPreviousNode();
    public void setPreviousNode(CityNode previousNode);

    public float[] getColor();
    public void setColor(float[] color);
}

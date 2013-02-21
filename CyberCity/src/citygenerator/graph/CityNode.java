package citygenerator.graph;

import java.util.ArrayList;

public interface CityNode {

	public ArrayList<CityEdge> getAdjacentNodes();
	public void addAdjacentNode(CityEdge to);
	
	public String getName();
	
	public void setName(String name);
	
	public Point3D getCoordinates();
	
	public void setCoordinates(Point3D coordinates);
}

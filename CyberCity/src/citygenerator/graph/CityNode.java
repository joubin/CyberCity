package citygenerator.graph;

import java.util.ArrayList;

public interface CityNode {

	public ArrayList<CityNode> getAdjacentNodes();
	public void addAdjacentNode(CityNode to);
	
	public String getName();
	
	public void setName(String name);
	
	public Point3D getCoordinates();
	
	public void setCoordinates(Point3D coordinates);
}

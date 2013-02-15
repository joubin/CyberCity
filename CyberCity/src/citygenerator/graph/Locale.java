package citygenerator.graph;

import java.awt.Point;

public interface Locale {
	
	/**
	 * Sets the physical size allotted to the Locale.
	 * @param	sizes	the x and z coordinates will set the size of the Locale
	 */
	public void setSize(Point3D sizes);
	
	/**
	 * Gets the Point representing the size of the Locale
	 * @return	point representing the size
	 */
	public Point3D getSize();
	
	/**
	 * @param 	name	the name of the Locale. This will be used as a unique identifier
	 */
	public void setName(String name);
	
	/**
	 * @return	a string (unique) that is the name of the Locale
	 */
	public String getName();

	/**
	 * This function asks the city generator for a point in world space for a new node to be placed.
	 * If the city generator accepts this Point as a valid location, it will call setNext(...) with a valid Point3D
	 * @return the requested x/z coordinate point for the next node
	 * @see Locale#setNext(Point3D)
	 */
	public Point.Double requestPoint();
	
	/**
	 * This function is called by the city generator with a Point3D that will be the final coordinates of the CityNode
	 * <p>
	 * Note: This function should also update internal edges and data structures
	 * @param point	the final coordinates of the CityNode that will be created
	 * @return	The final CityNode that is created by this function
	 */
	public CityNode setNext(Point3D point);
	
	/**
	 * @return	the maximum amount of entities that this collection can hold.
	 */
	public int getMaxSize();

	/**
	 * @param maxSize	sets the maximum number of nodes allowed in this data structure
	 */
	public void setMaxSize(int maxSize);
	
	/**
	 * @return	a Point representing the minimum x and z values of the Locale
	 */
	public Point3D getMinimumPoint();
	
	/**
	 * @param point	a Point representing the minimum x and z values of the Locale
	 */
	public void setMinimumPoint(Point3D point);
	
	/**
	 * This function is called first by the city generator to run initialization code and set the location of the first CityNode
	 * @param startPoint	the starting Point for the first CityNode
	 * @return	a newly created CityNode
	 */
	public CityNode setFirst(Point3D startPoint);
	
	/**
	 * This function will return a CityNode that is on the edge of the Locale that is on the same side as otherPoint
	 * @param otherPoint	the Point3D that is on the same side of the Locale as the returned CityNode
	 * @return	a CityNode that is on the same side of the Locale as otherPoint
	 */
	public CityNode getEdgeNode(Point3D otherPoint);
	
//	/**
//	 * This function will analyze a point and determine if it is "occupied".
//	 * <p>
//	 * "Occupied" will usually simply mean whether or not the point is inside of the Locale's bounds, but not always.
//	 * @param point
//	 * @return
//	 */
//	public boolean isPointOccupied(Point3D point);
}

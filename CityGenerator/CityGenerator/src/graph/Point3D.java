package graph;

public class Point3D{

	public Double x, y, z;
	
	public Point3D(Double x, Double y, Double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double distance(Point3D coordinates) {
		return Math.sqrt(coordinates.x*x + coordinates.y*y + coordinates.z*z);
	}
	
	
}

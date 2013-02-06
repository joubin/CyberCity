package generator;

import graph.CityEdge;

import graph.CityNode;
import graph.Graph;
import graph.Point3D;

import java.awt.Point;
import java.util.Random;

public class RandomChain extends Graph {
	
	private int maxSize;

	public RandomChain(String name){
		setName(name);
	}

	@Override
	public CityNode setNext(Point3D point) {

		CityNode cn = new Building(name + "-" + getNodes().size(), point);
		CityEdge ce = new Road();
		
		if(lastNode != null) {
			addEdge(lastNode, cn, ce);
		}
		
		this.lastNode = cn;
		addNode(cn);
		
		return cn;
	}

	@Override
	public Point.Double requestPoint(){
		Point.Double p = 
				new Point.Double(
				minimumPoint.x + new Random().nextInt(size.x.intValue()), 
				minimumPoint.z + new Random().nextInt(size.z.intValue())
				);
		return p;
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
	public CityNode setFirst(Point3D startPoint) {

		CityNode cn = new Building(name + "-" + getNodes().size(), startPoint);
		CityEdge ce = new Road();
		
		if(lastNode != null) {
			addEdge(lastNode, cn, ce);
		}
		
		this.lastNode = cn;
		addNode(cn);
		
		return cn;
	}
}

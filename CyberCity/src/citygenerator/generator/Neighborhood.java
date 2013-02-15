//package citygenerator.generator;
//
//import java.awt.Point;
//import java.util.Random;
//
//import citygenerator.graph.CityEdge;
//import citygenerator.graph.CityNode;
//import citygenerator.graph.Graph;
//import citygenerator.graph.Point3D;
//
//public class Neighborhood extends Graph {
//
//	private Random random = new Random(System.currentTimeMillis() + this.hashCode());
//	
//	private int maxSize;
//	private boolean isMovingLeft = false;
//	private double radiansFromPosX;
//
//	private int xVarMax = 24;
//	private int xVarMin = 16;
//	private int zVarMax = 24;
//	private int zVarMin = 16;
//	
//	public Neighborhood (String name){
//		setName(name);
//	}
//	
//	public CityNode getLastNode(){
//		return lastNode;
//	}
//
//	@Override
//	public CityNode setFirst(Point3D startPoint){
////		double lowX, highX;
////		double lowY, highY;
////		double startX, startY;
////		
////		lowX = topLeft.x + size.x/10.0 + 1;
////		highX = topLeft.x + size.x - size.x/10.0;
////		lowY = topLeft.y + size.y/10.0 + 1;
////		highY = topLeft.y + size.y - size.y/10.0;
////		
////		boolean isOnHorizontalEdge = random.nextBoolean();
////		boolean isOnLow = random.nextBoolean();
////		
////		if(isOnHorizontalEdge){
////			startX = topLeft.x + random.nextInt(size.x.intValue());
////			
////			if(startX > topLeft.x + (size.x/2)){
////				isMovingLeft = true;
////			} else
////				isMovingLeft = false;
////			
////			//on "top" edge
////			if(isOnLow){
////				startY = lowY;		
////			} 
////			
////			//on "bottom" edge
////			else {
////				startY = highY;
////			}
////		} else {
////			startY = topLeft.y + random.nextInt(size.y.intValue());
////			
////			//on "left" edge
////			if(isOnLow){
////				startX = lowX;	
////				isMovingLeft = false;
////			} 
////			
////			//on "right" edge
////			else {
////				startX = highX;
////				isMovingLeft = true;
////			}
////		}
//		
//		setAngle(new Point.Double(startPoint.x, startPoint.z),
//				new Point.Double(minimumPoint.x + size.x/2, minimumPoint.z + size.z/2));
//
//		
//		this.lastNode = new Building(name + "-" + getNodes().size(), startPoint);
//		addNode(lastNode);
//		return lastNode;
//	}
//	
//	@Override
//	public CityNode setNext(Point3D point) {
//		
//		CityNode cn = new Building(name + "-" + getNodes().size(), point);
//		CityEdge ce = new Road();
//		
//		addEdge(lastNode, cn, ce);
//		
//		this.lastNode = cn;
//		addNode(cn);
//		
//		return cn;
//		
//
////		
////		//Point p = new Point(topLeft.x + new Random().nextInt(size.x), topLeft.y + new Random().nextInt(size.y));
////		CityNode cn = new Building(name + "-" + getNodes().size(), p);
////		CityEdge ce = new Road();
////		
////		addEdge(lastNode, cn, ce);
////		
////		this.lastNode = cn;
////		addNode(cn);
////		
////		height += zVariance;
////		
////		return cn;
//	}
//
//	@Override
//	public int getMaxSize() {
//		return maxSize;
//	}
//
//	@Override
//	public void setMaxSize(int maxSize) {
//		this.maxSize = maxSize;
//	}
//
//	public void setAngle(Point.Double from, Point.Double to){
//		this.radiansFromPosX = 
//		Math.atan(
//			(double)
//			(
//				(
//					(double)(to.y - from.y)
//				)
//				/
//				(
//					(double)(to.x - from.x)
//				)
//			)
//		);
//	}
//
//	@Override
//	public Point.Double requestPoint() {
//		double newX, newZ;
//		int xVariance, zVariance;
//		double modifier;
//		modifier = isMovingLeft ? -1 : 1;
//		xVariance = random.nextInt(xVarMax - xVarMin) + xVarMin;
//		zVariance = random.nextInt(zVarMax - zVarMin) + zVarMin;
//	
//		
//		xVariance *= random.nextInt() % 2;
//		zVariance *= random.nextInt() % 2;
//				
//		newX = lastNode.getCoordinates().x + (int)(modifier * Math.cos(radiansFromPosX) * 20.0) + xVariance;
//		newZ = lastNode.getCoordinates().z + (int)(modifier * Math.sin(radiansFromPosX) * 20.0) + zVariance;
//		
//		Point.Double p = new Point.Double(newX, newZ);
//		
//		if(isMovingLeft){
//			if(newX > lastNode.getCoordinates().x)
//				isMovingLeft = false;
//		} else {
//			if(newX < lastNode.getCoordinates().x)
//				isMovingLeft = true;
//		}
//		
//		setAngle(new Point.Double(lastNode.getCoordinates().x, lastNode.getCoordinates().z), p);
//		return p;
//	}
//}

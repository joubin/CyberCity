package citygenerator.generator;

import java.awt.Point;
import java.util.Random;

import citygenerator.graph.CityNode;
import citygenerator.graph.Graph;
import citygenerator.graph.Point3D;

public class CityGenerator {

	private Random rand = new Random();
	private HeightMapGenerator heightMapGenerator;
	private double[][] heightMap;
	
	private Downtown parent =  new Downtown("parent");
	
	public CityGenerator(HeightMapGenerator hmg) {
		heightMap = hmg.getMap();
		rand.setSeed(System.currentTimeMillis());
	}

	public Graph getTerribleGraph(double minX, double minY, double minZ,
                                  double width, double height, double depth,
                                  int minNodes, int maxNodes,
                                  int minSubs, int maxSubs,
                                  double minSubSize, double maxSubSize)
										throws IllegalAccessException, InstantiationException {

        System.out.print("Generating terribly laid out graph...");
		parent.setMinimumPoint(new Point3D(minX, minY, minZ));
		parent.setMaxSize(maxNodes * maxSubs);
		parent.setSize(new Point3D(width-minX, height-minY, depth-minZ));		
		
		int randSubs;
		if(maxSubs - minSubs > 0){
			randSubs = rand.nextInt(maxSubs - minSubs);
		} else
			randSubs = 0;
		
		Point3D lastConnectPoint = null;
		CityNode lastConnector = null;
		Downtown lastDowntown = null;
		for(int i = 0; i < randSubs + minSubs; i++){
			Downtown sg = new Downtown("downtown" + i);
			
			int numberOfNodes = (int) (rand.nextFloat() * (float) (maxNodes - minNodes)) + minNodes;
			
			double size = rand.nextInt((int) (maxSubSize - minSubSize)) + minSubSize;
			
			Point3D corner = null;
			//while(!isLocationValid(corner, new Point3D(size,size,size))) {
			double startX = (double)rand.nextInt((int) (width-size));
			double startZ = (double)rand.nextInt((int) (depth-size));
			double startY = getHeight(startX,startZ);
			
			corner = 
				new Point3D(
					startX, 
					startY, 
					startZ);
			//}
			
			sg.setMinimumPoint(corner);
			sg.setMaxSize(numberOfNodes);
			sg.setSize(new Point3D(size, size, size));
			
			for (Integer j = 0; j < numberOfNodes; j++) {
				CityNode next;
				if(j == 0) {
					next = sg.setFirst(corner);
				} else {	
					Point.Double xz = sg.requestPoint();
					next = sg.setNext(new Point3D(xz.x, getHeight(xz.x, xz.y), xz.y));
				}
				
				if(j == numberOfNodes-1){
					if(lastConnectPoint == null) {
						lastConnectPoint = new Point3D(corner.x + size, corner.y, corner.z + size);
						lastDowntown = sg;
					} else {
						CityNode connectNode = sg.getEdgeNode(lastConnectPoint);
						
						lastConnector = lastDowntown.getEdgeNode(connectNode.getCoordinates());
						
						Road connector = new Road();
						connector.setNodes(lastConnector, connectNode);
                        connector.setLength(lastConnector.getCoordinates().distance(connectNode.getCoordinates()));
						parent.addEdge(lastConnector, connectNode, connector);
						
						lastConnectPoint = new Point3D(corner.x + size, corner.y, corner.z + size);
						lastDowntown = sg;
					}
				}	
			}
			
			parent.addSubGraph(sg);			
		}
        System.out.println(" done");
		return parent;
	}

//	private boolean isLocationValid(Point3D corner, Point3D size) {
//		if (corner == null)
//			return false;
//		
//		for(Graph sg : parent.getSubGraphs()){
//			//If any of the new corners are in an existing subgraph, return false
//			if(	sg.isPointOccupied(corner) ||
//				sg.isPointOccupied(new Point3D(corner.x + size.x, corner.y, corner.z)) ||
//				sg.isPointOccupied(new Point3D(corner.x, corner.y, corner.z + size.z)) ||
//				sg.isPointOccupied(new Point3D(corner.x + size.x, corner.y, corner.z + size.z))
//				)
//				return false;
//			
//			// TODO If any of the existing subgraphs have corners inside of the new subgraph, return false
//		}
//		
//		return true;
//	}

	public void setHeightMapGenerator(HeightMapGenerator hmg){
		this.heightMapGenerator = hmg;
	}
	
	public HeightMapGenerator getHeightMapGenerator() {
		return heightMapGenerator;
	}
	
	public double getHeight(double x, double z){
		return (heightMap[(int) x][(int) z]);
	}

	public double[][] getHeightMap() {
		return heightMap;
	}

	public void setHeightMap(double[][] heightMap) {
		this.heightMap = heightMap;
	}
	
}

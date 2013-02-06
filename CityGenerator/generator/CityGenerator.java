package generator;

import java.awt.Point;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.Random;

import graph.CityEdge;
import graph.CityNode;
import graph.Graph;
import graph.Point3D;

public class CityGenerator {

	Random rand = new Random();
	private HeightMapGenerator heightMapGenerator;
	private HeightMapGenerator hmg;
	private double[][] heightMap;
	
	public CityGenerator(HeightMapGenerator hmg) {
		this.hmg = hmg;
		heightMap = hmg.getMap();
		rand.setSeed(System.currentTimeMillis());
	}

	public Graph getRandomChainGraph(	double minX, double minY, double minZ,
										double width, double height, double depth,
										int minNodes, int maxNodes,
										int minSubs, int maxSubs,
										double minSubSize, double maxSubSize)
										throws IllegalAccessException, InstantiationException {

		Downtown parent =  new Downtown("parent");
		
		parent.setMinimumPoint(new Point3D(minX, minY, minZ));
		parent.setMaxSize(maxNodes * maxSubs);
		parent.setSize(new Point3D(width-minX, height-minY, depth-minZ));		
		
		int randSubs;
		if(maxSubs - minSubs > 0){
			randSubs = rand.nextInt(maxSubs - minSubs);
		} else
			randSubs = 0;
		
		for(int i = 0; i < randSubs + minSubs; i++){
			Downtown sg = new Downtown("rc" + i);
			
			int numberOfNodes = (int) (rand.nextFloat() * (float) (maxNodes - minNodes)) + minNodes;
			
			double size = rand.nextInt((int) (maxSubSize - minSubSize)) + minSubSize;
			
			double startX = (double)rand.nextInt((int) (width-size));
			double startZ = (double)rand.nextInt((int) (depth-size));
			double startY = getHeight(startX,startZ);
			
			Point3D corner = 
					new Point3D(
						startX, 
						startY, 
						startZ);
			
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
				
//				if(j == 0){
//					if(parent.getLastNode() != null){
//						parent.addEdge(parent.getLastNode(), next, new Road());
//					}
//					parent.setLastNode(next);
//				}	
			}
			
			parent.addSubGraph(sg);			
		}
		
		return parent;
	}


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

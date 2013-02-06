package generator;

import graph.CityEdge;
import graph.CityNode;

public class Road implements CityEdge {
	
	private double length;
	private CityNode from, to;
	private float waterFlowCap, electricFlowCap;
	
	public Road(){

	}
	
	@Override
	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public double getLength() {
		return length;
	}

	@Override
	public void setNodes(CityNode from, CityNode to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public CityNode getFromNode() {
		return from;
	}

	@Override
	public CityNode getToNode() {
		return to;
	}

	@Override
	public float getElectricFlowCap() {
		return electricFlowCap;
	}
	
	@Override
	public void setElectricFlowCap(float cap){
		this.electricFlowCap = cap;
	}

	@Override
	public float getWaterFlowCap() {
		return waterFlowCap;
	}
	
	@Override
	public void setWaterFlowCap(float cap){
		this.waterFlowCap = cap;
	}
	
	@Override
	public CityEdge getReverse(){
		Road reverseRoad = new Road();
		reverseRoad.setNodes(to, from);
		reverseRoad.setLength(length);
		reverseRoad.setElectricFlowCap(getElectricFlowCap());
		reverseRoad.setWaterFlowCap(getWaterFlowCap());
		return reverseRoad;
	}
	
}

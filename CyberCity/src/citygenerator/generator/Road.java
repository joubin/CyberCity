package citygenerator.generator;

import citygenerator.graph.CityEdge;
import citygenerator.graph.CityNode;

public class Road implements CityEdge {
	
	private double length;
	private CityNode from, to;
	private float waterFlowCap, electricFlowCap;
    private float[] color = {0F, 1F, 0F};
    private CityEdge reverse;

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
	public CityEdge createReverse(){
		Road reverseRoad = new Road();
		reverseRoad.setNodes(to, from);
		reverseRoad.setLength(length);
		reverseRoad.setElectricFlowCap(getElectricFlowCap());
		reverseRoad.setWaterFlowCap(getWaterFlowCap());
		return reverseRoad;
	}

    @Override
    public float[] getColor() {
        return color;
    }

    @Override
    public void setColor(float[] color, boolean isReverse){
       this.color = color;
        if(reverse != null && !isReverse)
            reverse.setColor(color, true);
    }

    @Override
    public CityEdge getReverse() {
        return reverse; 
    }

    @Override
    public void setReverse(CityEdge reverse) {
        this.reverse = reverse;
    }

    public String toString(){
		return "From " + from + " to " + to;
	}
	
}

package citygenerator.graph;

public interface CityEdge {

	public void setLength(double d);
	
	public double getLength();
	
	public void setNodes(CityNode from, CityNode to);
	
	public CityNode getFromNode();
	
	public CityNode getToNode();
	
	public float getElectricFlowCap();
	public void setElectricFlowCap(float cap);
	
	public float getWaterFlowCap();
	public void setWaterFlowCap(float cap);
	
	public CityEdge createReverse();

    public float[] getColor();
    public void setColor(float[] color, boolean isReverse);

    public CityEdge getReverse();
    public void setReverse(CityEdge reverse);
}

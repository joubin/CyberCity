package generator;

public class NodeFactory<N> {
	
	Class<N> nodeClass;
	
	public NodeFactory(Class<N> nodeClass){
		this.nodeClass = nodeClass;
	}
	
	public N createInstance() throws InstantiationException, IllegalAccessException {
		return (N) nodeClass.newInstance();
	}

}

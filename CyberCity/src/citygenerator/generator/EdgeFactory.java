package citygenerator.generator;

public class EdgeFactory<N> {
	
	Class<N> edgeClass;
	
	public EdgeFactory(Class<N> edgeClass){
		this.edgeClass = edgeClass;
	}
	
	public N createInstance() throws InstantiationException, IllegalAccessException {
		return (N) edgeClass.newInstance();
	}

}
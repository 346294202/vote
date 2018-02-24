package wj.flydoc;

public interface ApiGroup {
	
	String getName();

	Iterable<ApiMethod> getMethods();

}

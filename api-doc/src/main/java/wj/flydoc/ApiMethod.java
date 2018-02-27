package wj.flydoc;

public interface ApiMethod {

	Iterable<ApiMethodParameter> getParameters();
	String getReturn();
	
	String getName();
	String getMethod();
	String getPath();

}

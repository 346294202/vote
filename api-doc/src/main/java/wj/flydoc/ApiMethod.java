package wj.flydoc;

public interface ApiMethod {

	Iterable<ApiMethodParameter> getParameters();
	
	String getName();
	String getMethod();
	String getPath();

}

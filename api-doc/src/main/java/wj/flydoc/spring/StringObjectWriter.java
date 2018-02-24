package wj.flydoc.spring;

public interface StringObjectWriter {

	void beginObject(int tabs);

	void endObject(int tabs);

	void writeValue(int tabs, Class<?> type, String desc, Boolean required, String defVal);

	void beginField(int tabs, String name);

	void endField(int tabs);

	void beginList(int tabs);

	void endList(int tabs);

}
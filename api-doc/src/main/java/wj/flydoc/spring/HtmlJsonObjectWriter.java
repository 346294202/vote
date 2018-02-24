package wj.flydoc.spring;

import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class HtmlJsonObjectWriter implements StringObjectWriter {
	
	public final static String TAB = "&nbsp;";
	
	private StringBuilder sb = new StringBuilder();
	
	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#beginObject(int)
	 */
	@Override
	public void beginObject(int tabs) {
		sb.append("{<br/>");
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#endObject(int)
	 */
	@Override
	public void endObject(int tabs) {
		sb.append(tabStr(tabs));
		sb.append("}");
	}

	@Override
	public String toString() {
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#writeValue(int, java.lang.Class, java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public void writeValue(int tabs, Class<?> type, String desc, Boolean required, String defVal) {
		Vector<String> parts = new Vector<>();
		if(type != null)
			parts.add(Formats.format(type));
		if(!StringUtils.isBlank(desc))
			parts.add(desc);
		if(required != null)
			parts.add(Formats.formatRequired(required));
		if(!StringUtils.isBlank(defVal))
			parts.add(Formats.formatDefVal(defVal));		
		sb.append(String.join(",", parts));		
	}

	private String tabStr(int tabs) {
		return StringUtils.repeat(TAB, tabs);
	}


	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#beginField(int, java.lang.String)
	 */
	@Override
	public void beginField(int tabs, String name) {
		sb.append(tabStr(tabs+1));
		sb.append(name+":");
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#endField(int)
	 */
	@Override
	public void endField(int tabs) {
		sb.append("<br/>");
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#beginList(int)
	 */
	@Override
	public void beginList(int tabs) {
		sb.append("[");
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.StringWriter#endList(int)
	 */
	@Override
	public void endList(int tabs) {
		sb.append("]");
	}
}

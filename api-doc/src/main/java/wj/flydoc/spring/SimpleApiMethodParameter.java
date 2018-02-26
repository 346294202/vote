package wj.flydoc.spring;

public class SimpleApiMethodParameter extends SpringMVCParameter {

	private String name;
	private String type;
	private String desc;
	private String required;
	private String defVal;

	public SimpleApiMethodParameter(String name, String format, String desc, String formatRequired,
			String formatDefVal) {
		super();
		this.name = name;
		this.type = format;
		this.desc = desc;
		this.required = formatRequired;
		this.defVal = formatDefVal;
	}

	@Override
	public String getDefVal() {
		return defVal;
	}

	@Override
	public String getRequired() {
		return required;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getName() {
		return name;
	}

}

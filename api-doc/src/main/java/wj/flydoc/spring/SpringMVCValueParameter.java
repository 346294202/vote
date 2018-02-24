package wj.flydoc.spring;

import java.lang.reflect.Parameter;

import org.apache.commons.lang3.StringUtils;

import wj.flydoc.ApiParam;

public abstract class SpringMVCValueParameter extends SpringMVCParameter {

	private Parameter rawParam;
	private ApiParam apiParam;
	
	public SpringMVCValueParameter(Parameter rawParam, ApiParam apiParam) {
		super();
		this.rawParam = rawParam;
		this.apiParam = apiParam;
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.ValueParameter#getDefVal()
	 */
	@Override
	public String getDefVal() {
		if(apiParam != null 
				&& !StringUtils.isBlank(apiParam.defaultValue()))
			return "缺省"+apiParam.defaultValue();
		return null;
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.ValueParameter#getRequired()
	 */
	@Override
	public String getRequired() {
		boolean r = getSubRequired();
		if(apiParam != null)
			r  = apiParam.required();
		return r ? "必须" : "可选";
	}

	protected abstract boolean getSubRequired();

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.ValueParameter#getType()
	 */
	@Override
	public String getType() {
		Class<?> type = rawParam.getType();
		return Formats.format(type);
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.ValueParameter#getDesc()
	 */
	@Override
	public String getDesc() {
		if(apiParam != null 
				&& !StringUtils.isBlank(apiParam.desc()))
			return apiParam.desc();
		return null;
	}

	/* (non-Javadoc)
	 * @see wj.flydoc.spring.ValueParameter#getName()
	 */
	@Override
	public String getName() {
		if(apiParam != null 
				&& !StringUtils.isBlank(apiParam.name()))
			return apiParam.name();
		String name = getSubName();
		if(!StringUtils.isBlank(name))
			return name;
		return rawParam.getName();
	}

	protected abstract String getSubName();

}

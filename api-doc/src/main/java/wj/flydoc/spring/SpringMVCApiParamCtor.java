package wj.flydoc.spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class SpringMVCApiParamCtor extends SpringMVCParameter {
	
	private Parameter rawParam;
	private Constructor<?> ctor;

	public SpringMVCApiParamCtor(Parameter rawParam, Constructor<?> ctor) {
		super();
		this.rawParam = rawParam;
		this.ctor = ctor;
	}

	@Override
	public String getDefVal() {
		return null;
	}

	@Override
	public String getRequired() {
		return null;
	}

	@Override
	public String getType() {
		StringObjectWriter writer = new HtmlObjectWriter();
		ObjectWalker walker = new ObjectCtorWalker(ctor);
		try {
			walker.accept(0, writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}

	@Override
	public String getDesc() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

}

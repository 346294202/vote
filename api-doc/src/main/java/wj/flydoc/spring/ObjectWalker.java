package wj.flydoc.spring;

import java.util.Collection;
import java.util.Date;

public abstract class ObjectWalker {
	
	class Param {
		
		String name;
		Class<?> clazz;
		Class<?> elementClazz;
		String desc;
		Boolean required;
		String defVal;
		public Param(String name, Class<?> clazz, Class<?> elementClazz, String desc, Boolean required, String defVal) {
			super();
			this.name = name;
			this.clazz = clazz;
			this.elementClazz = elementClazz;
			this.desc = desc;
			this.required = required;
			this.defVal = defVal;
		}

	}

	public void accept(int tabs, StringObjectWriter writer) throws Exception {
		
		writer.beginObject(tabs);
		
		for(Param p:findParams()) {
			writer.beginField(tabs, p.name);
			if(isValue(p.clazz)) {
				writer.writeValue(tabs,
						p.clazz,
						p.desc,
						p.required,
						p.defVal);
			} else if(Collection.class.equals(p.clazz) && p.elementClazz != null) {
				writer.beginList(tabs);
				if(isValue(p.elementClazz)) {
					writer.writeValue(tabs, p.elementClazz, null, null, null);
				} else {
					ObjectPropertiesWalker walker = new ObjectPropertiesWalker(p.elementClazz);
					walker.accept(tabs+1, writer);
				}
				writer.endList(tabs);
			} else {
				
			}
			
			writer.endField(tabs);
		}
		writer.endObject(tabs);
	}


	protected abstract Collection<Param> findParams() throws Exception;

	protected boolean isValue(Class<?> clazz) {
		return clazz.isPrimitive()
				|| clazz.equals(String.class)
				|| clazz.equals(Date.class)
				|| Number.class.isAssignableFrom(clazz)
				|| Boolean.class.isAssignableFrom(clazz)
				|| Enum.class.isAssignableFrom(clazz);
	}

}
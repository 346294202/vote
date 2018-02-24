package wj.flydoc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiParam {
	
	String name() default "";

	String desc() default "";

	boolean required() default false;

	String defaultValue() default "";
	
	int max() default 0;
	int min() default 0;
}

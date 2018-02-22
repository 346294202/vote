package com.leoyon.doc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiParamIgnore {
	
	String name() default "";

	String desc() default "";

	boolean required() default true;

	String defaultValue() default "";
	
	int max() default 0;
	int min() default 0;
}
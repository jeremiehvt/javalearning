package com.exemple.demo.annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestingAnnotation {
	Class<? extends Throwable> expected() default NoExceptionExpected.class;
	long timeout() default -1;
	public static class NoExceptionExpected extends Throwable {}
	
}

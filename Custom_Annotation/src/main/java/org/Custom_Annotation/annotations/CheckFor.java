package org.Custom_Annotation.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Custom annotation representing each field level annotations 
 * @author priyambadam
 *
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
public @interface CheckFor {

	ValidationType[] type();
}

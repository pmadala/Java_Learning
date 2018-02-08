package org.Custom_Annotation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.ReportAsSingleViolation;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ReportAsSingleViolation
public @interface ValidDate {
	String message() default "invalid date";

    Class<?>[] groups() default {};

    boolean optional() default false;
}

package org.Custom_Annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

/**
 * Custom annotation representing date field validations
 * @author priyambadam
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ValidDateValidator.class)
public @interface ValidDate {
	String message() default "invalid date";

	String[] format() default {"DDMMYYYY"};
}

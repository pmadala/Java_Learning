package org.Custom_Annotation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.ReportAsSingleViolation;
/**
 * Custom annotation representing email field validations
 * @author priyambadam
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ReportAsSingleViolation
public @interface Email {

    String message() default "Invalide format for email";

    Class<?>[] groups() default {};

}
package org.Custom_Annotation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Custom annotation for entire class level annotation for Pan class
 * @author priyambadam
 *
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface PanValidate {
	Severity Severity() default Severity.HIGH;
	
	@NonNull
	String fullname();
	
	String fatherName() default "";
	
	@NonNull
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	String panNumber();
	
	Class<? extends Date> dob();
	
	@NonNull
	String issuedBy();
}

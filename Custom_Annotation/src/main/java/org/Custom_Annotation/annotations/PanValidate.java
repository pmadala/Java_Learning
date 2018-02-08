package org.Custom_Annotation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;

import org.Custom_Annotation.annotations.Severity;
import org.eclipse.jdt.annotation.NonNull;

import javax.validation.Constraint;
import javax.validation.constraints.Pattern;

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

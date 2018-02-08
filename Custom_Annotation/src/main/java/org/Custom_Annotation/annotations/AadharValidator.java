package org.Custom_Annotation.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.constraints.Pattern;

import org.eclipse.jdt.annotation.NonNull;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface AadharValidator {
	Severity Severity() default Severity.HIGH;
	
	String fullname();
	
	String gender();
	
	@Pattern(regexp = "(^$|[0-9]{10})")
	String aadharNumber();
	
	
	
	Class<?> dob() ;
	
	@NonNull
	String address();
}

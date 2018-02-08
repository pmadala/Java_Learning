package org.Custom_Annotation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.eclipse.jdt.annotation.NonNull;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface BankStatementValidator {
	Severity Severity() default Severity.MEDIUM;
	
	@NonNull
	String customerName();
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	String mobileNumber();
	
	@NotNull
	@Email
	String email();
	
	Class<?> transactions() default Object.class;
	
	@NonNull
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	String accountNumber();
	
	@NonNull
	String address();
}

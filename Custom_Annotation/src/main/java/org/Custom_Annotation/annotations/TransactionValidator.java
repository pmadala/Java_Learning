package org.Custom_Annotation.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.constraints.NotNull;

import org.eclipse.jdt.annotation.NonNull;
/**
 * Custom annotation for entire class level annotation for Transaction class
 * @author priyambadam
 *
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface TransactionValidator {
	@NotNull
	String id();
	
	String description() default "default description";
	
	@NonNull
	double amount() default 0.0;
	
	@NonNull
	Class<?> transactionTime();
}

package org.Custom_Annotation.annotations;


public interface ConstraintValidator<T1, T2> {

	void initialize(T1 t1);

	boolean isValid(T2 t2, ConstraintValidator constraintValidatorContext);

}

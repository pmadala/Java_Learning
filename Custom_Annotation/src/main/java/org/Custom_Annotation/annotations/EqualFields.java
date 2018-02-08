package org.Custom_Annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.Custom_Annotation.model.Document;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EqualFieldsValidator.class})
public @interface EqualFields {
 
    String message() default "consistancy check failed";
 /*   Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};*/
 
    String matchField();
    String baseField();
    //Class<? extends Annotation> mappingAnnotation() ;
    
    Class<? extends Document> matchClass();
}
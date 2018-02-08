package org.Custom_Annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.Custom_Annotation.model.Document;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualFields {
 
    String message() default "consistancy check failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
 
    String baseField();
 
    String matchField();
    
    Class<? extends Document> matchClass();
}
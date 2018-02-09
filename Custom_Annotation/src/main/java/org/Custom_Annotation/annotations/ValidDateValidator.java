package org.Custom_Annotation.annotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validator representing email field validations
 * @author priyambadam
 *
 */
public class ValidDateValidator implements ConstraintValidator<ValidDate, Date> {

   

    public void initialize(ValidDate validDate) {
        
    }

    public boolean isValid(Date value, ConstraintValidatorContext constraintValidatorContext) {

        boolean validDate = isValidFormat("yyyy/MM/dd", value.toString());
        return validDate;
    }

    private static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (value != null){
                date = sdf.parse(value);
                if (!value.equals(sdf.format(date))) {
                    date = null;
                }
            }

        } catch (ParseException ex) {
        }
        return date != null;
    }
}

package org.Custom_Annotation.annotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ValidDateValidator implements ConstraintValidator<ValidDate, Date> {

    private Boolean isOptional;

    public void initialize(ValidDate validDate) {
        this.isOptional = validDate.optional();
    }

    public boolean isValid(Date value, ConstraintValidator constraintValidatorContext) {

        boolean validDate = isValidFormat("yyyy/MM/dd", value.toString());

        return isOptional ? (validDate || (StringUtils.isBlank(value.toString()))) : validDate;
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

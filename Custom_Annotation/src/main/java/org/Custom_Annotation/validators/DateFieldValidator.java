package org.Custom_Annotation.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Validation for Date field
 * @author priyambadam
 *
 */
public class DateFieldValidator implements FieldValidator {

	@Override
	public boolean validate(String value) {
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

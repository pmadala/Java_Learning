package org.Custom_Annotation.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.Custom_Annotation.CustomAnnotationException;
/**
 * Validation for Date field
 * @author priyambadam
 *
 */
public class DateFieldValidator implements FieldValidator {

	private String dateFormat = "";
	
	public DateFieldValidator(String value){
		dateFormat = value;
	}
	
	@Override
	public boolean validate(String value) throws CustomAnnotationException {
		return isValidFormat("yyyy/MM/dd", value);
	}
	 private static boolean isValidFormat(String format, String value) throws CustomAnnotationException {
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
	        	throw new CustomAnnotationException(ex.getMessage());
	        }
	        return date != null;
	    }

}

package org.Custom_Annotation.validators;

import org.Custom_Annotation.annotations.ValidationType;

/**
 * A factory class returning the validator instance
 * @author priyambadam
 *
 */
public class FieldValidatorFactory {

	public static FieldValidator getInstance(ValidationType type) throws Exception {
		FieldValidator validator = null;
		switch (type) {
		case NOT_NULL:
			validator = new NotnullFieldValidation();
			break;
		case NUMBER:
			validator = new NumberFieldValidator();
			break;
		case ALPHANEUMERIC:
			validator = new AlphaNeumericFieldValidator();
			break;
		case AADHAR_NUMBER_LENGTH:
			validator = new AadharNumberLengthValidator();
			break;
		case Gender:
			validator = new GenderFieldValidator();
			break;
		case PAN_NUMBER_LENGTH:
			validator = new PanNumberLengthValidator();
			break;
		case ADDRESS_FIELD_LENGTH:
			validator = new AddressLengthValidator();
			break;
		case DATE:
			validator = new DateFieldValidator();
			break;
		case EMAIL:
			validator = new EmailFieldValidator();
			break;
		case EQUAL_FIELD:
			validator = new EqualFieldValidator();
			break;
		default:
			throw new Exception("Invalide Validation Type");

		}
		return validator;
	}
}

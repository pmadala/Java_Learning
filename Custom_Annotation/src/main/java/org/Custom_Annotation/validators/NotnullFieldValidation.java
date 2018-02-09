package org.Custom_Annotation.validators;

import org.apache.commons.lang3.StringUtils;

/**
 * Validation for not null
 * @author priyambadam
 *
 */
public class NotnullFieldValidation extends AbstractFieldValidator{

	@Override
	public boolean validate(String value) {
		return StringUtils.isNotBlank(value);
	}
}

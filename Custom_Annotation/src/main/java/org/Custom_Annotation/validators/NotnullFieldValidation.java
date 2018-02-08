package org.Custom_Annotation.validators;

import org.apache.commons.lang3.StringUtils;

public class NotnullFieldValidation extends AbstractFieldValidator{

	@Override
	public boolean validate(String value) {
		return StringUtils.isNotBlank(value);
	}

}

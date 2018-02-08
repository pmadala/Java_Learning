package org.Custom_Annotation.validators;

import java.util.Arrays;

public class GenderFieldValidator extends AbstractFieldValidator{

	@Override
	public boolean validate(String value) {
		return Arrays.asList("M","F").contains(value);
	}

}

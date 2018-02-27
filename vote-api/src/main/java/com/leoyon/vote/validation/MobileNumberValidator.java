package com.leoyon.vote.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.leoyon.vote.util.Checks;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {

	@Override
	public void initialize(MobileNumber constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Checks.checkMobile(value);
	}

}

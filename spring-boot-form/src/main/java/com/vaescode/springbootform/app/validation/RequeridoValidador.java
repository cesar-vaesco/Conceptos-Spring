package com.vaescode.springbootform.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		/*
		 * if (value == null || value.isEmpty() || value.isBlank()) { return false; }
		 */

		if (value == null || !StringUtils.hasText(value)) {
			return false;
		}
		return true;
	}

}

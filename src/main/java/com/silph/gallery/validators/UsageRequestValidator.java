package com.silph.gallery.validators;

import com.silph.gallery.model.UsageRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * UsageRequestValidator
 */
@Component
public class UsageRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UsageRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "clientName", "required");
        ValidationUtils.rejectIfEmpty(errors, "clientSurname", "required");
        ValidationUtils.rejectIfEmpty(errors, "clientEmail", "required");
    }

    
}
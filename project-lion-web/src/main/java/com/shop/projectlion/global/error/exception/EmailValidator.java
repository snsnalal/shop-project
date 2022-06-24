package com.shop.projectlion.global.error.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@RequiredArgsConstructor
public abstract class EmailValidator<T> implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {

            try{
                doValidation((T) target, errors);
            }catch (RuntimeException e){
                throw e;
            }

    }
    protected abstract void doValidation(final T dto, final Errors errors);
}

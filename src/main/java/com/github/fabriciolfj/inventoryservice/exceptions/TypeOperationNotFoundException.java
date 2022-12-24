package com.github.fabriciolfj.inventoryservice.exceptions;

import com.github.fabriciolfj.inventoryservice.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class TypeOperationNotFoundException extends DomainException {

    public TypeOperationNotFoundException() {
        super(Errors.ERROR_1.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}

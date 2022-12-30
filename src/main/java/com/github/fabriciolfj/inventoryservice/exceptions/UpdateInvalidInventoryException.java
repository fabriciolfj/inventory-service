package com.github.fabriciolfj.inventoryservice.exceptions;

import com.github.fabriciolfj.inventoryservice.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class UpdateInvalidInventoryException extends DomainException {

    public UpdateInvalidInventoryException() {
        super(Errors.ERROR_2.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}

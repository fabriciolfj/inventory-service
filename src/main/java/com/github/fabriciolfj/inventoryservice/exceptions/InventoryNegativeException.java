package com.github.fabriciolfj.inventoryservice.exceptions;

import com.github.fabriciolfj.inventoryservice.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class InventoryNegativeException extends DomainException {

    public InventoryNegativeException() {
        super(Errors.ERROR_2.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}

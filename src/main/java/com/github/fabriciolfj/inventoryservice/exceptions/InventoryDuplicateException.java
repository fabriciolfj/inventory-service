package com.github.fabriciolfj.inventoryservice.exceptions;

import com.github.fabriciolfj.inventoryservice.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class InventoryDuplicateException extends DomainException {

    public InventoryDuplicateException() {
        super(Errors.ERROR_3.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}

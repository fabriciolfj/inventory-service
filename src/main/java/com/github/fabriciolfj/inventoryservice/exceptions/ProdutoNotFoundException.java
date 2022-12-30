package com.github.fabriciolfj.inventoryservice.exceptions;

import com.github.fabriciolfj.inventoryservice.exceptions.enums.Errors;
import org.springframework.http.HttpStatus;

public class ProdutoNotFoundException extends DomainException {

    public ProdutoNotFoundException() {
        super(Errors.ERROR_4.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}

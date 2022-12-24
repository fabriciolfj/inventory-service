package com.github.fabriciolfj.inventoryservice.exceptions;


import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private int status;

    public DomainException(final String message, final int status) {
        super(message);
        this.status = status;
    }
}

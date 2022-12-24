package com.github.fabriciolfj.inventoryservice.exceptions.enums;

import java.util.ResourceBundle;

public enum Errors {

    ERROR_1;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("messages/exceptions");
        return bundle.getString(this.name() + ".message");
    }
}

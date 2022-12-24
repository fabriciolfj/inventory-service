package com.github.fabriciolfj.inventoryservice.entities;

import java.time.LocalDateTime;

public record InventoryEntity(
        String code,
        String product,
        int quantity,
        TypeOperation typeOperation,
        LocalDateTime registration) {

    public boolean isExit() {
        return this.typeOperation.getDescription().equals(TypeOperation.EXIT.getDescription());
    }

    public boolean isEntrance() {
        return this.typeOperation.getDescription().equals(TypeOperation.ENTRANCE.getDescription());
    }
}

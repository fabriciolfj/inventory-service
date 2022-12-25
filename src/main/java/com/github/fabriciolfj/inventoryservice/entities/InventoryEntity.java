package com.github.fabriciolfj.inventoryservice.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InventoryEntity {

    @EqualsAndHashCode.Include
    private String code;
    private String product;
    private int quantity;
    private int balance;
    private TypeOperation typeOperation;
    private LocalDateTime registration;

    public boolean isExit() {
        return this.typeOperation.getDescription().equals(TypeOperation.EXIT.getDescription());
    }

    public boolean isEntrance() {
        return this.typeOperation.getDescription().equals(TypeOperation.ENTRANCE.getDescription());
    }

    public InventoryEntity updateBalance(final InventoryEntity entity) {
        if (isExit()) {
            this.balance = entity.getBalance() - this.quantity;
            return this;
        }

        this.balance = entity.getBalance() + this.quantity;
        return this;
    }
}
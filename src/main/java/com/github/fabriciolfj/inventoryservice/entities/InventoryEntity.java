package com.github.fabriciolfj.inventoryservice.entities;


import com.github.fabriciolfj.inventoryservice.exceptions.UpdateInvalidInventoryException;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InventoryEntity {

    private static final int BALANCE_INIT = 0;

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
        return process(entity.getBalance());
    }

    public InventoryEntity initBalance() {
        return process(BALANCE_INIT);
    }

    private InventoryEntity process(final int balance) {
        if (isExit() && balance == BALANCE_INIT) {
            throw new UpdateInvalidInventoryException();
        }

        if (isExit()) {
            this.balance = balance - this.quantity;
            return this;
        }

        this.balance = balance + this.quantity;
        return this;
    }
}
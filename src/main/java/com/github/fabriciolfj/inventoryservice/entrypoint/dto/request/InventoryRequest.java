package com.github.fabriciolfj.inventoryservice.entrypoint.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {

    private String product;
    private long quantity;
    private String operation;
}

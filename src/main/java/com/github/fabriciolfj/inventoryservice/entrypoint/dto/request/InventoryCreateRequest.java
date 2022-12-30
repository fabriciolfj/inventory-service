package com.github.fabriciolfj.inventoryservice.entrypoint.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCreateRequest {

    private String product;
    private long quantity;
}

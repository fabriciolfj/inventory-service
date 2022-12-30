package com.github.fabriciolfj.inventoryservice.providers.database.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "inventory")
public class InventoryData {

    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private String code;
    private String product;
    private long entrance;
    private long exit;
    private long balance;
    @Column("date_registration")
    private LocalDateTime dateRegistration;
}

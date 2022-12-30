package com.github.fabriciolfj.inventoryservice.providers.database.repository;

import com.github.fabriciolfj.inventoryservice.providers.database.data.InventoryData;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface InventoryRepository extends ReactiveCrudRepository<InventoryData, Long> {

    @Lock(LockMode.PESSIMISTIC_WRITE)
    Mono<InventoryData> findFirstByProductOrderByDateRegistrationDesc(final String code);
}

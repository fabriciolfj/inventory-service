package com.github.fabriciolfj.inventoryservice.providers.database.repository;

import com.github.fabriciolfj.inventoryservice.providers.database.data.InventoryData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface InventoryRepository extends ReactiveCrudRepository<InventoryData, Long> {

    Mono<InventoryData> findFirstByProductOrderByDateRegistrationDesc(final String code);
}

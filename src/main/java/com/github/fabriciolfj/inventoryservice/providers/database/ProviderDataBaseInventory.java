package com.github.fabriciolfj.inventoryservice.providers.database;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.providers.database.mappers.InventoryDataMapper;
import com.github.fabriciolfj.inventoryservice.providers.database.repository.InventoryRepository;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderFindProductInventory;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderSaveInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProviderDataBaseInventory implements ProviderSaveInventory, ProviderFindProductInventory {

    private final InventoryRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Mono<InventoryEntity> process(final InventoryEntity entity) {
        return Mono.just(entity).map(InventoryDataMapper::toData)
                .flatMap(repository::save)
                .thenReturn(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Mono<InventoryEntity> process(final String code) {
        return repository.findFirstByProductOrderByDateRegistrationDesc(code)
                .map(InventoryDataMapper::toEntity);
    }
}

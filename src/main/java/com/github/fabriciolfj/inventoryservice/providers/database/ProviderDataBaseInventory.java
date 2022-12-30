package com.github.fabriciolfj.inventoryservice.providers.database;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.providers.database.mappers.InventoryDataMapper;
import com.github.fabriciolfj.inventoryservice.providers.database.repository.InventoryRepository;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderFindProductInventory;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderSaveInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ProviderDataBaseInventory implements ProviderSaveInventory, ProviderFindProductInventory {

    private static final Random random = new Random();
    private final InventoryRepository repository;

    @Override
    public Mono<InventoryEntity> process(final InventoryEntity entity) {
        return Mono.just(entity).map(InventoryDataMapper::toData)
                .flatMap(repository::save)
                .map(InventoryDataMapper::toEntity);
    }

    @Override
    public Mono<InventoryEntity> process(final String code) {
        return Mono.just(code)
                .delayElement(Duration.ofMillis(random.nextInt(1000)))
                .flatMap(repository::findFirstByProductOrderByDateRegistrationDesc)
                .map(InventoryDataMapper::toEntity);
    }
}

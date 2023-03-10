package com.github.fabriciolfj.inventoryservice.usecase.domain;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.exceptions.InventoryDuplicateException;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderFindProductInventory;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderSaveInventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistryInventoryUseCase {

    private final ProviderSaveInventory providerSaveInventory;
    private final ProviderFindProductInventory providerFindProductInventory;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Mono<Void> execute(final InventoryEntity entity) {
        return Mono.just(entity)
                .doOnNext(c -> log.info("Inventory executed {}", c.getCode()))
                .flatMap(c -> providerFindProductInventory.process(c.getProduct()))
                .flatMap(c -> Mono.error(new InventoryDuplicateException()))
                .switchIfEmpty(Mono.defer(() -> providerSaveInventory.process(entity)))
                .doOnSuccess(c -> log.info("Inventory save success {}", c))
                .then();
    }
}

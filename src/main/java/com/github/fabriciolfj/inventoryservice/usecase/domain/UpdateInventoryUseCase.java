package com.github.fabriciolfj.inventoryservice.usecase.domain;

import com.github.fabriciolfj.inventoryservice.entities.InventoryEntity;
import com.github.fabriciolfj.inventoryservice.exceptions.ProdutoNotFoundException;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderFindProductInventory;
import com.github.fabriciolfj.inventoryservice.usecase.ProviderSaveInventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalApplicationListener;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateInventoryUseCase {

    private final ProviderSaveInventory providerSaveInventory;
    private final ProviderFindProductInventory providerFindProductInventory;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Mono<InventoryEntity> execute(final InventoryEntity entity) {
        return Mono.defer(() -> providerFindProductInventory.process(entity.getProduct()))
                .flatMap(c -> {
                    var result = c.getValidInventory();
                    return providerSaveInventory.process(entity.updateBalance(result));
                }).switchIfEmpty(Mono.error(new ProdutoNotFoundException()));
    }
}

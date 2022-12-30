package com.github.fabriciolfj.inventoryservice.entrypoint.controller;

import com.github.fabriciolfj.inventoryservice.entrypoint.dto.request.InventoryCreateRequest;
import com.github.fabriciolfj.inventoryservice.entrypoint.dto.request.InventoryRequest;
import com.github.fabriciolfj.inventoryservice.entrypoint.mappers.InventoryDTOMapper;
import com.github.fabriciolfj.inventoryservice.usecase.domain.RegistryInventoryUseCase;
import com.github.fabriciolfj.inventoryservice.usecase.domain.UpdateInventoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/v1/inventories")
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final RegistryInventoryUseCase useCase;
    private final UpdateInventoryUseCase useCaseUpdate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> process(@Valid @RequestBody final InventoryCreateRequest request) {
        return useCase.execute(InventoryDTOMapper.toEntity(request))
                .doOnSuccess(result -> log.info("Inventory created success"))
                .doOnError(e -> log.info("Fail creae inventory, details: {}", e.getMessage()));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> processUpdate(@Valid @RequestBody final InventoryRequest request) {
        return useCaseUpdate.execute(InventoryDTOMapper.toEntity(request))
                .doOnSuccess(result -> log.info("Inventory created success {}", result))
                .flatMap(r -> Mono.empty());
    }
}

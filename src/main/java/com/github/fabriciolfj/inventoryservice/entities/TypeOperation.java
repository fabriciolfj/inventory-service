package com.github.fabriciolfj.inventoryservice.entities;

import com.github.fabriciolfj.inventoryservice.exceptions.TypeOperationNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TypeOperation {

    EXIT("exit"),
    ENTRANCE("entrance");

    private final String description;

    public static final TypeOperation toEnum(final String description) {
        return Stream.of(TypeOperation.values())
                .filter(c -> c.description.equals(description))
                .findAny()
                .orElseThrow(() -> new TypeOperationNotFoundException());
    }
}

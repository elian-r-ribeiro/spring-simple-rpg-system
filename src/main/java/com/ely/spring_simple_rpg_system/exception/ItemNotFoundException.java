package com.ely.spring_simple_rpg_system.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super(
                "Item não encontrado com o id " + id
        );
    }
}

package com.ely.spring_simple_rpg_system.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super(
                "Jogador não encontrado com o id " + id
        );
    }
}

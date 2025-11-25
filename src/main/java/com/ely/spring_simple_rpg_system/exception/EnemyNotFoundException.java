package com.ely.spring_simple_rpg_system.exception;

public class EnemyNotFoundException extends RuntimeException {
    public EnemyNotFoundException(Long id) {
        super(
                "Nenhum inimigo encontrado com o id " + id
        );
    }
}

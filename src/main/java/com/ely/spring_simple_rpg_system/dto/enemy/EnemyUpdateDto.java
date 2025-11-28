package com.ely.spring_simple_rpg_system.dto.enemy;

import jakarta.validation.constraints.Size;

public record EnemyUpdateDto(
        @Size(min = 3, message = "O nome do inimigo deve ter no mínimo 3 caracteres.")
        @Size(max = 20, message = "O nome do inimigo deve ter no máximo 20 caracteres.")
        String name,
        Long hp,
        Long ap,
        Long dp,
        Long xpDrop
) {
}

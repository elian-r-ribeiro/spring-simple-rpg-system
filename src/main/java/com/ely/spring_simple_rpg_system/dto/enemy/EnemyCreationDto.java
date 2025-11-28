package com.ely.spring_simple_rpg_system.dto.enemy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnemyCreationDto(
        @NotBlank(message = "O nome do inimigo é obrigatório.")
        @Size(min = 3, message = "O nome do inimigo deve ter no mínimo 3 caracteres.")
        @Size(max = 20, message = "O nome do inimigo deve ter no máximo 20 caracteres.")
        String name,

        @NotNull(message = "O HP do inimigo é obrigatório.")
        Long hp,

        @NotNull(message = "A AP do inimigo é obrigatório.")
        Long ap,

        @NotNull(message = "A DP do inimigo é obrigatório.")
        Long dp,

        @NotNull(message = "O drop de XP do inimigo é obrigatório.")
        Long xpDrop
) {
}

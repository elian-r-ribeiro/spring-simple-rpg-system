package com.ely.spring_simple_rpg_system.dto.item;

import com.ely.spring_simple_rpg_system.enums.ItemUsages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemCreationDto(
        @NotBlank(message = "O nome do item é obrigatório.")
        String itemName,

        @NotNull(message = "Qual o tipo de uso do item é obrigatório.")
        ItemUsages itemUsage,

        @NotNull(message = "O valor do efeito do item é obrigatório.")
        Long effectValue
) {
}

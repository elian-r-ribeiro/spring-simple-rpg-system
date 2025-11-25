package com.ely.spring_simple_rpg_system.dto.item;

import com.ely.spring_simple_rpg_system.enums.ItemUsages;
import jakarta.validation.constraints.Size;

public record ItemUpdateDto(
        @Size(min = 3, message = "O nome do item deve ter no mínimo 3 caracteres.")
        @Size(max = 20, message = "O nome do item deve ter no máximo 20 caracteres.")
        String itemName,
        ItemUsages itemUsage,
        Long effectValue
) {
}

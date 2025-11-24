package com.ely.spring_simple_rpg_system.dto.item;

import com.ely.spring_simple_rpg_system.enums.ItemUsages;

public record ItemDto(
        Long itemId,
        String itemName,
        ItemUsages itemUsage,
        Long effectValue
) {
}

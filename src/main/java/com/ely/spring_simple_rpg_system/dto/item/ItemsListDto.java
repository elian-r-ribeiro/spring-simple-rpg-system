package com.ely.spring_simple_rpg_system.dto.item;

import com.ely.spring_simple_rpg_system.entity.Item;

import java.util.List;

public record ItemsListDto(
        List<Item> items
) {
}

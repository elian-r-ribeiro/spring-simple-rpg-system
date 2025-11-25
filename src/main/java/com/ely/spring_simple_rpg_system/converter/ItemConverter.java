package com.ely.spring_simple_rpg_system.converter;

import com.ely.spring_simple_rpg_system.dto.item.ItemCreationDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemUpdateDto;
import com.ely.spring_simple_rpg_system.entity.Item;

public class ItemConverter {

    public static Item fromCreationDtoToItem(ItemCreationDto data) {
        return new Item(
                null,
                data.itemName(),
                data.itemUsage(),
                data.effectValue()
        );
    }

    public static ItemDto fromItemToDto(Item item) {
        return new ItemDto(
                item.getItemId(),
                item.getItemName(),
                item.getItemUsage(),
                item.getEffectValue()
        );
    }

    public static void mergeChanges(Item item, ItemUpdateDto dto) {
        if(dto.itemName() != null) item.setItemName(dto.itemName());
        if(dto.itemUsage() != null) item.setItemUsage(dto.itemUsage());
        if(dto.effectValue() != null) item.setEffectValue(dto.effectValue());
    }
}

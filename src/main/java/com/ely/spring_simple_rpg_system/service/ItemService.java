package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.converter.ItemConverter;
import com.ely.spring_simple_rpg_system.dto.item.ItemCreationDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemDto;
import com.ely.spring_simple_rpg_system.entity.Item;
import com.ely.spring_simple_rpg_system.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    public ItemDto createItem(ItemCreationDto data) {

        final Item newItem = itemRepository.save(ItemConverter.fromCreationDtoToItem(data));

        return ItemConverter.fromItemToDto(newItem);
    }
}

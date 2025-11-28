package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.converter.ItemConverter;
import com.ely.spring_simple_rpg_system.dto.item.ItemCreationDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemDto;
import com.ely.spring_simple_rpg_system.dto.item.ItemUpdateDto;
import com.ely.spring_simple_rpg_system.entity.Item;
import com.ely.spring_simple_rpg_system.exception.ItemNotFoundException;
import com.ely.spring_simple_rpg_system.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    public ItemDto createItem(ItemCreationDto data) {

        final Item newItem = itemRepository.save(ItemConverter.fromCreationDtoToItem(data));

        return ItemConverter.fromItemToDto(newItem);
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemConverter::fromItemToDto)
                .toList();
    }

    public ItemDto getItemById(Long id) {
        return ItemConverter.fromItemToDto(findItemByIdOrThrowException(id));
    }

    public ItemDto updateItem(Long id, ItemUpdateDto data) {
        final Item item = findItemByIdOrThrowException(id);

        ItemConverter.mergeChanges(item, data);
        itemRepository.save(item);
        return ItemConverter.fromItemToDto(item);
    }

    public void deleteItem(Long id) {
        findItemByIdOrThrowException(id);
        itemRepository.deleteById(id);
    }

    public Item findItemByIdOrThrowException(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}

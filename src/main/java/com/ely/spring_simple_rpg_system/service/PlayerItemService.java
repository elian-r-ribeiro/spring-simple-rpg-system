package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.converter.ItemConverter;
import com.ely.spring_simple_rpg_system.converter.PlayerConverter;
import com.ely.spring_simple_rpg_system.dto.item.ItemsListDto;
import com.ely.spring_simple_rpg_system.dto.player.PlayerDto;
import com.ely.spring_simple_rpg_system.entity.Item;
import com.ely.spring_simple_rpg_system.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerItemService {

    private ItemService itemService;
    private PlayerService playerService;

    public PlayerDto addItemToPlayerItems(Long playerId, Long itemId) {
        final Player player = playerService.findPlayerByIdOrThrowException(playerId);
        final Item item = itemService.findItemByIdOrThrowException(itemId);

        player.getItems().add(item);
        return PlayerConverter.fromPlayerToDto(playerService.savePlayer(player));
    }

    public PlayerDto useItem(Long playerId, int itemPositionInList) {
        final Player player = playerService.findPlayerByIdOrThrowException(playerId);
        final Item item = player.getItems().get(itemPositionInList);

        item.getItemUsage().useItem(player, item);
        player.getItems().remove(itemPositionInList);
        return PlayerConverter.fromPlayerToDto(playerService.savePlayer(player));
    }

    public ItemsListDto getPlayerItems(Long id) {
        final Player player = playerService.findPlayerByIdOrThrowException(id);
        return ItemConverter.fromItemsListToItemListDto(player.getItems());
    }

}

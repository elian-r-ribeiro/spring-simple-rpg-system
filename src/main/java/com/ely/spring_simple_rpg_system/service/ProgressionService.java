package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProgressionService {

    private LoggerService loggerService;

    public void checkIfPlayerLeveledUpOnWin(List<String> logs, Player player) {
        if(player.getXp() >= player.getRequiredXpToNextLevel()) {
            loggerService.addPlayerLeveledUpLogs(logs);
            player.setLevel(player.getLevel() + 1L);
            player.setXp(0L);
            player.setRequiredXpToNextLevel(player.getRequiredXpToNextLevel() + 100);
            player.setMaxHp(player.getMaxHp() + 10);
            player.setAttackPower(player.getAttackPower() + 5);
            player.setDefensePower(player.getDefensePower() + 5);
        }
    }

    public void addEnemyXpDropToPlayerOnWin(List<String> logs, Player player, Long enemyXpDrop) {
        loggerService.addPlayerXpAddedLogs(logs, enemyXpDrop);
        player.setXp(player.getXp() + enemyXpDrop);
    }
}

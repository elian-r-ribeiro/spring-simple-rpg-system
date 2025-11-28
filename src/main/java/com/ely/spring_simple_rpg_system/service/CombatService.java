package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.dto.enemy.EnemyCombatDto;
import com.ely.spring_simple_rpg_system.entity.Enemy;
import com.ely.spring_simple_rpg_system.entity.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class CombatService {

    private PlayerService playerService;
    private EnemyService enemyService;
    private LoggerService loggerService;
    private ProgressionService progressionService;

    public List<String> executeCombat(Long playerId, Long enemyId) {
        final Player player = playerService.findPlayerByIdOrThrowException(playerId);
        final Enemy originalEnemy = enemyService.findEnemyByIdOrThrowException(enemyId);
        final EnemyCombatDto enemy = new EnemyCombatDto(originalEnemy.getName(), originalEnemy.getHp(),
                originalEnemy.getAp(), originalEnemy.getDp(), originalEnemy.getXpDrop());
        final List<String> logs = new ArrayList<>(List.of());
        final int playerRoll = ThreadLocalRandom.current().nextInt(1, 21);
        final int enemyRoll = ThreadLocalRandom.current().nextInt(1, 21);
        boolean isPlayerTurn = playerRoll >= enemyRoll;

        loggerService.addRollsAndWhoAttacksFirstToLogs(logs, enemyRoll, playerRoll,
                player.getName(), enemy.getName());
        executeCombatLoop(logs, player, enemy, isPlayerTurn);
        final boolean didPlayerWin = checkIfPlayerWon(player.getCurrentHp());
        if(didPlayerWin) {
            progressionService.addEnemyXpDropToPlayerOnWin(logs, player, enemy.getXpDrop());
            progressionService.checkIfPlayerLeveledUpOnWin(logs, player);
        } else {
            recoverPlayerAfterDefeat(player);
        }

        playerService.savePlayer(player);

        return logs;
    }
    
    private void executeCombatLoop(List<String> logs, Player player, EnemyCombatDto enemy, boolean isPlayerTurn) {

        while (player.getCurrentHp() > 0L && enemy.getHp() > 0L) {
            if(isPlayerTurn) {
                loggerService.addPlayerTurnBeginningLogs(logs, enemy.getHp(), enemy.getDp());
                applyDamageToEnemy(enemy, player);
                loggerService.addPlayerTurnEndingLogs(logs, player.getAttackPower(), enemy.getHp());
            } else {
                loggerService.addEnemyTurnBeginningLogs(logs, player.getCurrentHp(), player.getDefensePower());
                applyDamageToPlayer(player, enemy);
                loggerService.addEnemyTurnEndingLogs(logs, enemy.getAp(), player.getCurrentHp());
            }

            isPlayerTurn = !isPlayerTurn;
        }

        loggerService.addCombatEndingLogs(logs, player.getCurrentHp(), enemy.getHp());
    }

    private void applyDamageToEnemy(EnemyCombatDto enemy, Player player) {
        long damage = player.getAttackPower() - enemy.getDp();
        damage = Math.max(1, damage);

        enemy.setHp(enemy.getHp() - damage);
    }

    private void applyDamageToPlayer(Player player, EnemyCombatDto enemy) {
        long damage = enemy.getAp() - player.getDefensePower();
        damage = Math.max(0, damage);

        player.setCurrentHp(player.getCurrentHp() - damage);
    }

    private boolean checkIfPlayerWon(Long playerHp) {
        return playerHp > 0;
    }

    private void recoverPlayerAfterDefeat(Player player) {
        if(player.getCurrentHp() <= 0L) player.setCurrentHp(1L);
    }
}

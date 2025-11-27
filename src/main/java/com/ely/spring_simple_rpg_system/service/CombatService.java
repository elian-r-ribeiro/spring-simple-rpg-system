package com.ely.spring_simple_rpg_system.service;

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

    public List<String> executeCombat(Long playerId, Long enemyId) {
        List<String> logs = new ArrayList<>(List.of());
        final int playerRoll = ThreadLocalRandom.current().nextInt(1, 21);
        final int enemyRoll = ThreadLocalRandom.current().nextInt(1, 21);
        boolean isPlayerTurn = playerRoll >= enemyRoll;

        addRollsAndWhoAttacksFirstToLogs(logs, enemyRoll, playerRoll);
        executeCombatLoop(logs, playerId, enemyId, isPlayerTurn);

        return logs;
    }
    
    private void executeCombatLoop(List<String> logs, Long playerId, Long enemyId, boolean isPlayerTurn) {
        final Player player = playerService.findPlayerByIdOrThrowException(playerId);
        final Enemy enemy = enemyService.findEnemyByIdOrThrowException(enemyId);

        while (player.getPlayerCurrentHp() > 0L && enemy.getEnemyHp() > 0L) {
            System.out.println("Chegou aqui!");
            if(isPlayerTurn) {
                addPlayerTurnBeginningLogs(logs, enemy.getEnemyHp(), enemy.getEnemyDp());
                enemy.setEnemyHp(
                        enemy.getEnemyHp() - (player.getPlayerAttackPower() - enemy.getEnemyDp()));
                addPlayerTurnEndingLogs(logs, player.getPlayerAttackPower(), enemy.getEnemyHp());
            } else {
                addEnemyTurnBeginningLogs(logs, player.getPlayerCurrentHp(), player.getPlayerDefensePower());
                player.setPlayerCurrentHp(
                        player.getPlayerCurrentHp() - (enemy.getEnemyAp() - player.getPlayerDefensePower()));
                addEnemyTurnEndingLogs(logs, enemy.getEnemyAp(), player.getPlayerCurrentHp());
            }

            isPlayerTurn = !isPlayerTurn;
        }

        addCombatEndingLogs(logs, player.getPlayerCurrentHp(), enemy.getEnemyHp());

        if(player.getPlayerCurrentHp() >= 0L) player.setPlayerCurrentHp(1L);
    }

    private void addRollsAndWhoAttacksFirstToLogs(List<String> logs, int enemyRoll, int playerRoll) {
        logs.add("Rolagem do jogador: " + playerRoll);
        logs.add("Rolagem do inimigo: " + enemyRoll);
        logs.add(playerRoll >= enemyRoll ? "O jogador começa!" : "O inimigo começa!");
    }

    private void addPlayerTurnBeginningLogs(List<String> logs, Long enemyHp, Long enemyDp) {
        logs.add(
                "O jogador atacou! O HP do inimigo era: "
                        + enemyHp
                        + " e a D.P. no inimigo é: "
                        + enemyDp
        );
    }

    private void addPlayerTurnEndingLogs(List<String> logs, Long playerAp, Long enemyHp) {
        logs.add(
                "A A.P. do jogador é: "
                        + playerAp
                        + ". Com isso, o H.P. do inimigo agora é "
                        + enemyHp
                        + " (H.P. = H.P. - (A.P. - D.P.))"
        );
    }
    
    private void addEnemyTurnBeginningLogs(List<String> logs, Long playerHp, Long playerDp) {
        logs.add(
                "O inimigo atacou! O HP do jogador era: "
                        + playerHp
                        + " e a D.P. no jogador é: "
                        + playerDp
        );
    }
    
    private void addEnemyTurnEndingLogs(List<String> logs, Long enemyAp, Long playerHp) {
        logs.add(
                "A A.P. do inimigo é: "
                        + enemyAp
                        + ". Com isso, o H.P. do jogador agora é "
                        + playerHp
                        + " (H.P. = H.P. - (A.P. - D.P.))"
        );
    }

    private void addCombatEndingLogs(List<String> logs, Long playerHp, Long enemyHp) {
        if(playerHp <= 0L) {
            logs.add("O inimigo venceu e o jogador agora está com 1 de vida.");
        } else {
            logs.add("O jogador venceu e terminou com " + playerHp + " de vida.");
        }
    }
}

package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.entity.Enemy;
import com.ely.spring_simple_rpg_system.entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class CombatService {

    @PersistenceContext
    private EntityManager entityManager;
    private PlayerService playerService;
    private EnemyService enemyService;

    public List<String> executeCombat(Long playerId, Long enemyId) {
        final Player player = playerService.findPlayerByIdOrThrowException(playerId);
        final Enemy enemy = enemyService.findEnemyByIdOrThrowException(enemyId);
        final List<String> logs = new ArrayList<>(List.of());
        final int playerRoll = ThreadLocalRandom.current().nextInt(1, 21);
        final int enemyRoll = ThreadLocalRandom.current().nextInt(1, 21);
        boolean isPlayerTurn = playerRoll >= enemyRoll;

        entityManager.detach(enemy);

        addRollsAndWhoAttacksFirstToLogs(logs, enemyRoll, playerRoll);
        executeCombatLoop(logs, player, enemy, isPlayerTurn);
        final boolean didPlayerWin = checkIfPlayerWon(player.getPlayerCurrentHp());
        if(didPlayerWin) {
            addEnemyXpDropToPlayerOnWin(logs, player, enemy.getEnemyXpDrop());
            checkIfPlayerLeveldUpOnWin(logs, player);
        } else {
            ifPlayerLostRecoverHp(player);
        }

        playerService.playerRepository.save(player);

        return logs;
    }
    
    private void executeCombatLoop(List<String> logs, Player player, Enemy enemy, boolean isPlayerTurn) {

        while (player.getPlayerCurrentHp() > 0L && enemy.getEnemyHp() > 0L) {
            if(isPlayerTurn) {
                addPlayerTurnBeginningLogs(logs, enemy.getEnemyHp(), enemy.getEnemyDp());

                long damage = player.getPlayerAttackPower() - enemy.getEnemyDp();
                damage = Math.max(0, damage);

                enemy.setEnemyHp(enemy.getEnemyHp() - damage);
                addPlayerTurnEndingLogs(logs, player.getPlayerAttackPower(), enemy.getEnemyHp());
            } else {
                addEnemyTurnBeginningLogs(logs, player.getPlayerCurrentHp(), player.getPlayerDefensePower());

                long damage = enemy.getEnemyAp() - player.getPlayerDefensePower();
                damage = Math.max(0, damage);

                player.setPlayerCurrentHp(player.getPlayerCurrentHp() - damage);
                addEnemyTurnEndingLogs(logs, enemy.getEnemyAp(), player.getPlayerCurrentHp());
            }

            isPlayerTurn = !isPlayerTurn;
        }

        addCombatEndingLogs(logs, player.getPlayerCurrentHp(), enemy.getEnemyHp());
    }

    private boolean checkIfPlayerWon(Long playerHp) {
        return playerHp > 0;
    }

    private void ifPlayerLostRecoverHp(Player player) {
        if(player.getPlayerCurrentHp() <= 0L) player.setPlayerCurrentHp(1L);
    }

    private void checkIfPlayerLeveldUpOnWin(List<String> logs, Player player) {
        if(player.getPlayerXp() >= player.getPlayerRequiredXpToNextLevel()) {
            logs.add("O jogador subiu de nível!");
            player.setPlayerLevel(player.getPlayerLevel() + 1L);
            player.setPlayerXp(0L);
            player.setPlayerRequiredXpToNextLevel(player.getPlayerRequiredXpToNextLevel() + 100);
            player.setPlayerMaxHp(player.getPlayerMaxHp() + 10);
            player.setPlayerAttackPower(player.getPlayerAttackPower() + 5);
            player.setPlayerDefensePower(player.getPlayerDefensePower() + 5);
        }
    }

    private void addEnemyXpDropToPlayerOnWin(List<String> logs, Player player, Long enemyXpDrop) {
        logs.add("O jogador recebeu " + enemyXpDrop + " de XP.");
        player.setPlayerXp(player.getPlayerXp() + enemyXpDrop);
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
            logs.add("O inimigo venceu, terminando com " + enemyHp + " e o jogador agora está com 1 de vida.");
        } else {
            logs.add("O jogador venceu e terminou com " + playerHp + " de vida.");
        }
    }
}

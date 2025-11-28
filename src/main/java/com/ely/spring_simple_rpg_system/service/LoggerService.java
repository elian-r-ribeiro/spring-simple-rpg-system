package com.ely.spring_simple_rpg_system.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerService {

    public void addRollsAndWhoAttacksFirstToLogs(List<String> logs, int enemyRoll, int playerRoll,
                                                 String playerName, String enemyName) {
        logs.add("Jogador " + playerName + " entrou em combate com o inimigo " + enemyName + ".");
        logs.add("Rolagem do jogador: " + playerRoll);
        logs.add("Rolagem do inimigo: " + enemyRoll);
        logs.add(playerRoll >= enemyRoll ? "O jogador começa!" : "O inimigo começa!");
    }

    public void addPlayerXpAddedLogs(List<String> logs, Long enemyXpDrop) {
        logs.add("O jogador recebeu " + enemyXpDrop + " de XP.");
    }

    public void addPlayerLeveledUpLogs(List<String> logs) {
        logs.add("O jogador subiu de nível!");
    }

    public void addPlayerTurnBeginningLogs(List<String> logs, Long enemyHp, Long enemyDp) {
        logs.add(
                "O jogador atacou! O HP do inimigo era: "
                        + enemyHp
                        + " e a D.P. no inimigo é: "
                        + enemyDp
        );
    }

    public void addPlayerTurnEndingLogs(List<String> logs, Long playerAp, Long enemyHp) {
        logs.add(
                "A A.P. do jogador é: "
                        + playerAp
                        + ". Com isso, o H.P. do inimigo agora é "
                        + enemyHp
                        + " (H.P. = H.P. - (A.P. - D.P.))"
        );
    }

    public void addEnemyTurnBeginningLogs(List<String> logs, Long playerHp, Long playerDp) {
        logs.add(
                "O inimigo atacou! O HP do jogador era: "
                        + playerHp
                        + " e a D.P. no jogador é: "
                        + playerDp
        );
    }

    public void addEnemyTurnEndingLogs(List<String> logs, Long enemyAp, Long playerHp) {
        logs.add(
                "A A.P. do inimigo é: "
                        + enemyAp
                        + ". Com isso, o H.P. do jogador agora é "
                        + playerHp
                        + " (H.P. = H.P. - (A.P. - D.P.))"
        );
    }

    public void addCombatEndingLogs(List<String> logs, Long playerHp, Long enemyHp) {
        if(playerHp <= 0L) {
            logs.add("O inimigo venceu, terminando o combate com " + enemyHp + " de vida, " +
                    "e o jogador agora está com 1 de vida.");
        } else {
            logs.add("O jogador venceu e terminou com " + playerHp + " de vida.");
        }
    }
}

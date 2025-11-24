package com.ely.spring_simple_rpg_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String playerName;

    private Long playerAttackPower;

    private Long playerDefensePower;

    private Long playerCurrentHp;

    private Long playerMaxHp;

    private Long playerLevel;

    private Long playerXp;

    @ManyToMany
    @JoinTable(
            name = "players_items",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> playerItems = new ArrayList<>();

    @PrePersist
    public void appyDefaultStats() {

        if(playerAttackPower == null) this.playerAttackPower = 10L;
        if(playerDefensePower == null) this.playerDefensePower = 10L;
        if(playerCurrentHp == null) this.playerCurrentHp = 50L;
        if(playerMaxHp == null) this.playerMaxHp = 50L;
        if(playerLevel == null) this.playerLevel = 1L;
        if(playerXp == null) this.playerXp = 0L;
    }
}

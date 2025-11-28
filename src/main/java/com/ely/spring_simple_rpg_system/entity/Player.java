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
    private Long id;

    private String name;

    private Long attackPower;

    private Long defensePower;

    private Long currentHp;

    private Long maxHp;

    private Long level;

    private Long xp;

    private Long requiredXpToNextLevel;

    @ManyToMany
    @JoinTable(
            name = "players_items",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @PrePersist
    public void appyDefaultStats() {

        if(attackPower == null) this.attackPower = 10L;
        if(defensePower == null) this.defensePower = 10L;
        if(currentHp == null) this.currentHp = 50L;
        if(maxHp == null) this.maxHp = 50L;
        if(level == null) this.level = 1L;
        if(xp == null) this.xp = 0L;
        if(requiredXpToNextLevel == null) this.requiredXpToNextLevel = 100L;
    }
}

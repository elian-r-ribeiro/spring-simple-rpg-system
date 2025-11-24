package com.ely.spring_simple_rpg_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enemies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enemyId;

    private String enemyName;

    private Long enemyHp;

    private Long enemyAp;

    private Long enemyDp;

    private Long enemyXpDrop;
}

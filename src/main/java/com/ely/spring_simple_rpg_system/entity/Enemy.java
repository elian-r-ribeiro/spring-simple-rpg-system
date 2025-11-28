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
    private Long id;

    private String name;

    private Long hp;

    private Long ap;

    private Long dp;

    private Long xpDrop;
}

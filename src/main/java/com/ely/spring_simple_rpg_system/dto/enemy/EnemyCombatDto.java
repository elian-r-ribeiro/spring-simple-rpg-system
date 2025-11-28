package com.ely.spring_simple_rpg_system.dto.enemy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EnemyCombatDto {
    private String name;
    private Long hp;
    private Long ap;
    private Long dp;
    private Long xpDrop;
}

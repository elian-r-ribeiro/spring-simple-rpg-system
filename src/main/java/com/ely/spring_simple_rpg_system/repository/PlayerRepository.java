package com.ely.spring_simple_rpg_system.repository;

import com.ely.spring_simple_rpg_system.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {}

package com.ely.spring_simple_rpg_system.repository;

import com.ely.spring_simple_rpg_system.entity.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {}
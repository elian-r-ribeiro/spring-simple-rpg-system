package com.ely.spring_simple_rpg_system.service;

import com.ely.spring_simple_rpg_system.converter.EnemyConverter;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyCreationDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyDto;
import com.ely.spring_simple_rpg_system.dto.enemy.EnemyUpdateDto;
import com.ely.spring_simple_rpg_system.entity.Enemy;
import com.ely.spring_simple_rpg_system.exception.EnemyNotFoundException;
import com.ely.spring_simple_rpg_system.repository.EnemyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnemyService {

    private EnemyRepository enemyRepository;

    public EnemyDto createEnemy(EnemyCreationDto data) {
        final Enemy newEnemy = enemyRepository.save(EnemyConverter.fromCreationDtoToEnemy(data));

        return EnemyConverter.fromEnemyToDto(newEnemy);
    }

    public List<EnemyDto> getAllEnemies() {
        return enemyRepository.findAll().stream()
                .map(EnemyConverter::fromEnemyToDto)
                .toList();
    }

    public EnemyDto getEnemyById(Long id) {
        return EnemyConverter.fromEnemyToDto(findEnemyByIdOrThrowException(id));
    }

    public EnemyDto updateEnemy(Long id, EnemyUpdateDto data) {
        final Enemy enemy = findEnemyByIdOrThrowException(id);

        EnemyConverter.mergeChanges(enemy, data);
        enemyRepository.save(enemy);

        return EnemyConverter.fromEnemyToDto(enemy);
    }

    public void deleteEnemy(Long id) {
        findEnemyByIdOrThrowException(id);
        enemyRepository.deleteById(id);
    }

    public Enemy findEnemyByIdOrThrowException(Long id) {
        return enemyRepository.findById(id).orElseThrow(() -> new EnemyNotFoundException(id));
    }
}

package com.example.service.id_generation.dao;

import com.example.service.id_generation.entity.IDGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IDGenerationDao extends JpaRepository<IDGeneration, Long> {
    @Modifying
    @Transactional
    @Query("update IDGeneration set lastUsedNum = lastUsedNum + ?3, updateTime = now() where Id = ?1 and lastUsedNum = ?2 ")
    int updateLastUsedNumAndTime(Long id, Integer oldLastUsedNum, Integer increment);

    @Modifying
    @Transactional
    @Query("update IDGeneration set lastUsedNum = ?3, updateTime = now() where Id = ?1 and lastUsedNum = ?2 ")
    int reset(Long id, Integer oldLastUsedNum, Integer start);
}

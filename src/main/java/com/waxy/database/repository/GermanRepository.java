package com.waxy.database.repository;

import com.waxy.database.dto.GermanDto;
import com.waxy.database.entity.German;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GermanRepository extends JpaRepository<German,Integer> {
    @Query(value="SELECT * FROM german ge WHERE ge.sentence_id = ?1",nativeQuery = true)
    Set<German> findGermanBySentenceId(int id);

    @Query(value="SELECT   description FROM german ge WHERE ge.sentence_id = ?1",nativeQuery = true)
    Set<String> findGermanSentence(int id);
}

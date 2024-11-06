package com.waxy.database.repository;

import com.waxy.database.entity.EssentialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssentialLinkRepository extends JpaRepository<EssentialLink, Integer> {

    @Query(value = "SELECT e FROM EssentialLink e LEFT JOIN FETCH e.children WHERE e.parent IS NULL AND e.isActive = TRUE")
    List<EssentialLink> findAllActiveLinksWithChildren();
}

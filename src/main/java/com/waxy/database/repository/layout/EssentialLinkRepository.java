package com.waxy.database.repository.layout;

import com.waxy.database.entity.layout.EssentialLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssentialLinkRepository extends JpaRepository<EssentialLinkEntity, Integer> {

    @Query(value = "SELECT * FROM EssentialLink e LEFT JOIN FETCH e.children WHERE e.parent IS NULL AND e.isActive = TRUE", nativeQuery = true)
    List<EssentialLinkEntity> findAllActiveLinksWithChildren();

    @Query(value = "SELECT * FROM essential_link WHERE parent_id = ?1", nativeQuery = true)
    List<EssentialLinkEntity> findEssentialLinkByParentId(int id);
}

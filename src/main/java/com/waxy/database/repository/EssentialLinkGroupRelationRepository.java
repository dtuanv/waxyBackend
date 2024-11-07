package com.waxy.database.repository;

import com.waxy.database.entity.EssentialLinkGroupRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssentialLinkGroupRelationRepository extends JpaRepository<EssentialLinkGroupRelation, Integer> {

    @Query(value = "Select * FROM essential_link_group_relation WHERE essential_link_group_id = ?1", nativeQuery = true)
    List<EssentialLinkGroupRelation> findByGroupId(long id);

}

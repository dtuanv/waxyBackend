package com.waxy.database.repository;

import com.waxy.database.entity.EssentialLinkGroupRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssentialLinkGroupRelationRepository extends JpaRepository<EssentialLinkGroupRelation, Integer> {
}

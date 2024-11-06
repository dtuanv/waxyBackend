package com.waxy.database.repository;

import com.waxy.database.entity.EssentialLinkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssentialLinkGroupRepository extends JpaRepository<EssentialLinkGroup, Integer> {
}

package com.waxy.database.repository.layout;

import com.waxy.database.entity.layout.EssentialLinkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssentialLinkGroupRepository extends JpaRepository<EssentialLinkGroup, Integer> {
}

package com.waxy.database.repository;


import com.waxy.database.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

    @Query(value = "SELECT * FROM notice As n WHERE n.belong_to LIKE ?1",nativeQuery = true)
    Notice findANotice(String belongTo);
}

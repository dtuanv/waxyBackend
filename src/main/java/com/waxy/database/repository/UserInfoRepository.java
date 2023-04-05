package com.waxy.database.repository;

import com.waxy.database.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query(value= "SELECT * FROM user_info WHERE user_id = ?1", nativeQuery = true)
    UserInfo findByUserId(long userId);

    @Query(value = "SELECT uf.id, uf.name, uf.avatar,uf.user_id FROM user_info uf\n" +
            "JOIN users u ON uf.user_id = u.id \n" +
            "WHERE u.username = ?1 ",nativeQuery = true)
    UserInfo findByUsername(String username);
}

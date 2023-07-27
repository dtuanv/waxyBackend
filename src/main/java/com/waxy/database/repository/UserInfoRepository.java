package com.waxy.database.repository;

import com.waxy.database.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query(value="SELECT * FROM user_info WHERE business_id = ?1 AND  birthday LIKE ?2 ",nativeQuery = true)
    Set<UserInfo> findUserInfoTodayHasBirthday(long businessId,String today);
    @Query(value="SELECT * FROM user_info WHERE business_id = ?1 AND role != 'admin' ", nativeQuery = true)
    List<UserInfo> findUserInfoByBusinessId(long businessId);
    @Query(value= "SELECT * FROM user_info WHERE user_id = ?1", nativeQuery = true)
    UserInfo findByUserId(long userId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_info SET role =?1 WHERE id =?2",nativeQuery = true)
    void updateUserInfoRole(String role, long id);

        @Query(value = "SELECT * FROM user_info uf\n" +
            "JOIN users u ON uf.user_id = u.id \n" +
            "WHERE u.username = ?1 ",nativeQuery = true)
    UserInfo findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_info\n" +
            "SET rest_vacation = ?1\n" +
            "WHERE user_id = ?2", nativeQuery = true)
    void updateVacationInUserInfo(int restVacation, int userId);

    @Query(value = "SELECT rest_vacation FROM user_info WHERE user_id = ?1", nativeQuery = true)
    int findRestVacationByUserId(int userId);

    @Query(value="SELECT count(*) FROM user_info WHERE business_id = ?1", nativeQuery = true)
    int countUserOfBusiness(long businessId);
}

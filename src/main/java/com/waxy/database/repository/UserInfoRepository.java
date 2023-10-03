package com.waxy.database.repository;

import com.waxy.database.dto.UserDTO;
import com.waxy.database.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query(value="SELECT * FROM user_info WHERE business_id = ?1 AND \n" +
            "              EXTRACT(MONTH FROM TO_DATE(birthday, 'DD-MM-YYYY')) = ?2\n" +
            "              AND EXTRACT(DAY FROM TO_DATE(birthday, 'DD-MM-YYYY'))  = ?3"
           ,nativeQuery = true)
    Set<UserInfo> findUserInfoTodayHasBirthday(long businessId,int month, int day);
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
            "WHERE id = ?2", nativeQuery = true)
    void updateVacationInUserInfo(int restVacation, int userInfoId);

    @Query(value = "SELECT rest_vacation FROM user_info WHERE id = ?1", nativeQuery = true)
    int findRestVacationByUserInfoId(int userInfoId);

    @Query(value="SELECT count(*) FROM user_info WHERE business_id = ?1", nativeQuery = true)
    int countUserOfBusiness(long businessId);

    @Query(value = "SELECT * FROM user_info WHERE business_id = ?1 AND name ILIKE ?2 ", nativeQuery = true)
    Optional<UserInfo> findByNameAndBusinessId(int businessId, String fullName);
}

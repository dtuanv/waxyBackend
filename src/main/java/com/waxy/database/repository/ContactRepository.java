package com.waxy.database.repository;

import com.waxy.database.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(value="UPDATE CONTACT SET is_read = ?1 WHERE id = ?2 RETURNING id, message, mobil, gmail , create_at, is_read ", nativeQuery = true)
    Contact updateReadContactQuery(boolean isRead, long id);
}

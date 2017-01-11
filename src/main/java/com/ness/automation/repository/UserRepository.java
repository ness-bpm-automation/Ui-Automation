package com.ness.automation.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ness.automation.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE LOWER(u.username) = :username")
    User findOne(@Param("username") String username);
    
   /* @Query("SELECT c FROM Contact c WHERE userId = :userID")
    List<Contact> listAll(@Param("userID") long userID);

    @Modifying
    @Query("DELETE FROM Contact c WHERE c.uuid = :uuid")
    void deleteContact(@Param("uuid") String uuid);

    @Query("SELECT c FROM Contact c WHERE uuid = :uuid")
    Contact getContact(@Param("uuid") String uuid);*/
    
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :userId")
    void deleteUser(@Param("userId") long userId);
}

package com.etranslate.dao;

import com.etranslate.entity.Book;
import com.etranslate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yancychan on 17-6-4.
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {

    @Query("from User where username = :username")
    User getByUsername(@Param("username") String username);

    @Query("from User where username = :username and password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

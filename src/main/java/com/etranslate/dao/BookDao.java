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
public interface BookDao extends JpaRepository<Book, String> {

    @Query("from Book where word=:word")
    Book findByWord(@Param("word") String word);

    @Query("from Book where user = :user and word = :word")
    Book findByUsernameAndWord(@Param("user") User user, @Param("word") String word);

    @Query("from Book where user = :user")
    Book findByUsername(User user);
}

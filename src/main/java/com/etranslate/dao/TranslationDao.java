package com.etranslate.dao;

import com.etranslate.entity.Book;
import com.etranslate.entity.Translation;
import com.etranslate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yancychan on 17-6-4.
 */
@Repository
public interface TranslationDao extends JpaRepository<Translation, String> {

    @Query("from Translation where user = :user and word = :word and translateText = :translateText")
    Translation findByUserAndWordAndTranslateText(@Param("user") User user, @Param("word") String word, @Param("translateText") String translateText);

    @Query("from Translation where word = :word")
    Translation findByWord(@Param("word") String word);

    @Query("from Translation where tId = :tId")
    Translation findByTId(@Param("tId") String tId);

    @Query("from Translation where user = :user and url = :url and word = :word")
    Translation findByUserAndWordAndUrl(@Param("user") User user, @Param("url") String url, @Param("word") String word);
}

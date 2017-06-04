package com.etranslate.dao;

import com.etranslate.entity.Book;
import com.etranslate.entity.Interpretation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yancychan on 17-6-4.
 */
@Repository
public interface InterpretationDao extends JpaRepository<Interpretation, String> {
}

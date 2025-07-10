package com.quiz.dao;

import com.quiz.model.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {


    List<Question> findByCategory(String category);

    @Query(name="select * from question q where q.category = :category order by random()   ",nativeQuery = true)
    List<Question> findRandomByCategory(String category, PageRequest pageable);
}

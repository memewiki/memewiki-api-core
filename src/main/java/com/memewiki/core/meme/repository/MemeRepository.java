package com.memewiki.core.meme.repository;

import com.memewiki.core.meme.domain.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long>, MemeRepositoryCustom, QuerydslPredicateExecutor<Meme> {
}

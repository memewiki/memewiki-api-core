package com.memewiki.core.domain.meme.repository;

import com.memewiki.core.domain.meme.domain.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long>, MemeRepositoryCustom, QuerydslPredicateExecutor<Meme> {
}

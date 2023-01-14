package com.memewiki.core.domain.heart.repository;

import com.memewiki.core.domain.heart.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

}

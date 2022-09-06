package com.memewiki.core.Heart.repository;

import com.memewiki.core.Heart.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {

}

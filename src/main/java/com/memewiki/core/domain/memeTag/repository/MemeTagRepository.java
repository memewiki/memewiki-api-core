package com.memewiki.core.domain.memeTag.repository;

import com.memewiki.core.domain.memeTag.domain.MemeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemeTagRepository extends JpaRepository<MemeTag, Long> {
    List<MemeTag> findAllByMemeId(Long memeId);
}

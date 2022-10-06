package com.memewiki.core.domain.memeTag.domain;

import com.memewiki.core.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemeTag extends BaseEntity {
    @Id
    @Column(name = "meme_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

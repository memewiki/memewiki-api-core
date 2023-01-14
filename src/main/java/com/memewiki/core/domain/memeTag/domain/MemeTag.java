package com.memewiki.core.domain.memeTag.domain;

import com.memewiki.core.common.entity.BaseEntity;

import javax.persistence.*;

import com.memewiki.core.domain.tag.domain.Tag;
import com.memewiki.core.domain.meme.domain.Meme;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meme_id")
    private Meme meme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public MemeTag(Meme meme, Tag tag) {
        this.meme = meme;
        this.tag = tag;
    }
}
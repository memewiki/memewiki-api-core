package com.memewiki.core.domain.meme.domain;

import com.memewiki.core.common.entity.BaseEntity;

import javax.persistence.*;

import com.memewiki.core.domain.memeTag.domain.MemeTag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Meme extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memeUrl;
    private Integer memeHit;
    private Integer memeDownload;

    @OneToMany(mappedBy = "meme")
    private List<MemeTag> memeTagList = new ArrayList<>();

    public void hitsUp(){
        this.memeHit++;
    }

}

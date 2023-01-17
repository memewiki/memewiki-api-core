package com.memewiki.core.domain.meme.domain;

import com.memewiki.core.common.entity.BaseEntity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Meme extends BaseEntity {
    @Id
    @Column(name = "meme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memeUrl;
    private Integer memeHit;
    private Integer memeDownload;

    public Meme(String memeUrl, Integer memeHit, Integer memeDownload){
        this.memeUrl = memeUrl;
        this.memeHit = memeHit;
        this.memeDownload = memeDownload;
    }

    public void hitsUp(){
        this.memeHit++;
    }

}

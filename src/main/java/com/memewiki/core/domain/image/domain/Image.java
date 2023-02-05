package com.memewiki.core.domain.image.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
public class Image {
    private Long id;
    private String name;
    private String type;
    private Long memeId;
    private Blob picByte;

    public Image(Long memeId, String name, String type, Blob picByte) {
        this.memeId = memeId;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public Image(){}
}

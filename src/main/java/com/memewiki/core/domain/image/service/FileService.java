package com.memewiki.core.domain.image.service;

import com.memewiki.core.domain.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FileService {
    ImageRepository imageRepository;

    @Qualifier("webApplicationContext")
    ResourceLoader resourceLoader;

    public FileService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Long addImage (Map<String, Object> param) {
        return imageRepository.addImage(param);
    }

    public Map<String, Object> getImageById(Long id) {
        return imageRepository.findByMemeId(id);
    }
}

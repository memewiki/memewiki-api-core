package com.memewiki.core.domain.image.controller;

import com.memewiki.core.domain.image.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/images")
    public Long uploadImage(@RequestParam("imageFile") String file,
                            @RequestParam("memeId") Long memeId,
                            @RequestParam("name") String name) throws IOException {

        byte[] imageArray = null;
        final String BASE_64_PREFIX_PNG = "data:image/png;base64,";
        final String BASE_64_PREFIX_JPG = "data:image/jpg;base64,";
        final String BASE_64_PREFIX_JPEG = "data:image/jpeg;base64,";
        String type = null;
        Map<String, Object> params = new HashMap<>();
        try {
            String base64Url = String.valueOf(file);
            if (base64Url.startsWith(BASE_64_PREFIX_PNG)) {
                imageArray = Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX_PNG.length()));
                type = "image/png";
            }

            else if (base64Url.startsWith(BASE_64_PREFIX_JPG)) {
                imageArray = Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX_JPG.length()));
                type = "image/jpg";
            }

            if (base64Url.startsWith(BASE_64_PREFIX_JPEG)) {
                imageArray = Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX_JPEG.length()));
                type = "image/jpeg";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        params.put("name", name);
        params.put("type", type);
        params.put("picByte", imageArray);
        params.put("memeId", memeId);

        return fileService.addImage(params);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);

            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
        return outputStream.toByteArray();
    }
}

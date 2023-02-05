package com.memewiki.core.domain.image.controller;

import com.memewiki.core.domain.image.service.FileService;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/download")
public class FileDownloadController {

    FileService fileService;

    public FileDownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/images/{id}")
    public String getImage(@PathVariable Long id, HttpServletResponse response) throws SQLException, IOException {
        Map<String, Object> resultMap = fileService.getImageById(id);
//        Blob blob = (Blob) resultMap.get("pic_byte");
        byte[] arr = (byte[])resultMap.get("pic_byte");

        if (arr != null && arr.length > 0) {
            String base64Encode = byteToBase64(arr);
            base64Encode = "data:" + resultMap.get("type")+";base64," + base64Encode;
            return base64Encode;
        } else {
            return "";
        }
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {
        }

        return outputStream.toByteArray();
    }

    private static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;
        byte[] bytes = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;

            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // [byte를 base64로 인코딩 해주는 메소드]
    private static String byteToBase64(byte[] arr) {
        String result = "";
        try {
            result = Base64Utils.encodeToString(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.InternalException;
import com.example.demo.util.PathUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @RequestMapping("/image")
    public String uploadImage(MultipartFile file) {
        String path = PathUtils.IMAGE_FOLDER_PATH;
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty())
            throw new BadRequestException("Image is empty");
        if (filename.contains("..")) {
            // This is a security check
            throw new BadRequestException(
                    "Cannot store file with relative path outside current directory " + filename);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(path).resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return "images/upload/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalException("Upload failed");
        }
    }
}

package com.example.demo.util;

import com.example.demo.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/16
 */
public class PathUtils {
    private static final Logger log = LoggerFactory.getLogger("PathUtils");

    public static final String IMAGE_FOLDER_PATH;

    public static final String CSV_FOLDER_PATH;

    static {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) path = new File("");

            File upload = new File(path.getAbsolutePath(), "static/images/upload/");
            File csvFolder = new File(path.getAbsolutePath(), "static/csv");
            if ((!upload.exists() && !upload.mkdirs()) || (!csvFolder.exists() && !csvFolder.mkdirs()))
                log.error("Create image directory failed");
            IMAGE_FOLDER_PATH = upload.getAbsolutePath();
            CSV_FOLDER_PATH = csvFolder.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Upload file not found");
        }
    }
}

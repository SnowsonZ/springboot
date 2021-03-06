package com.example.springboot.controller.file;

import com.example.springboot.utils.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Snowson
 * @Date: 2018/6/22 15:47
 * @Description:
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    @Value("${tmpsavepath.import-error-path}")
    private String tempFile;
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("uploads")
    public String uploadFileMulti(@RequestParam("avatar") MultipartFile photo,
                                  @RequestParam("description") MultipartFile desc) {
        String fileKeyPath = System.currentTimeMillis() + "/";
        String photoName = "avatar." + photo.getOriginalFilename().split("\\.")[1];
        String descName = "desc." + desc.getOriginalFilename().split("\\.")[1];
        String filePath = FileUtils.PATH_FILE_UPLOAD + fileKeyPath;
        try {
            FileUtils.uploadFile(photo.getBytes(), filePath, photoName);
            FileUtils.uploadFile(desc.getBytes(), filePath, descName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取文件字节码出错...");
        }
        return "upload success!!!";
    }

    @PostMapping("upload")
    public String uploadFileSingle(@RequestParam("file") MultipartFile file) {
        String fileKeyPath = System.currentTimeMillis() + "/";
        String photoName = "avatar." + file.getOriginalFilename().split("\\.")[1];
        String filePath = FileUtils.PATH_FILE_UPLOAD + fileKeyPath;
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, photoName);
            String result = "{\"status\":\"upload success!!\"}";
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取文件字节码出错...");
            String result = "{\"status\":\"upload failed...\"}";
            return result;
        }
    }

    @PostMapping("list")
    public String uploadFileList(@RequestParam("desc") String desc, @RequestBody List<MultipartFile> files) {
        String fileKeyPath = System.currentTimeMillis() + "/";
        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename().split("\\.")[1];
            String filePath = FileUtils.PATH_FILE_UPLOAD + fileKeyPath;
            try {
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return "{\"status\":\"upload failed...\"}";
            }
        }
        return "{\"status\":\"upload success...\"}";
    }

    @PostMapping("lists")
    public String uploadFileStreamList(@RequestParam("file") MultipartFile file) {
        String fileKeyPath = System.currentTimeMillis() + "/";
        String fileName = System.currentTimeMillis() + "." + file.getOriginalFilename().split("\\.")[1];
        String filePath = FileUtils.PATH_FILE_UPLOAD + fileKeyPath;
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
            return "{\"status\":\"upload success...\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"upload failed...\"}";
        }
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileName") String fileName,
                             HttpServletResponse response) {
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        //输出流
        FileInputStream is;
        try (OutputStream os = response.getOutputStream()) {
            File file = new File(tempFile + "/" + fileName);
            is = new FileInputStream(file);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            os.write(buffer);
            os.flush();
        } catch (IOException e) {
            log.warn("downloadFile error, case: {}", e.getMessage());
        }
    }
}

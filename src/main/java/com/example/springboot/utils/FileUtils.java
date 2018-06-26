package com.example.springboot.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: Snowson
 * @Date: 2018/6/22 11:56
 * @Description:
 */
public class FileUtils {
    public static String PAHT_BASE_UPLOAD
            = "C:/Program Files (x86)/Apache Software Foundation/Apache2.2/htdocs/";
    public static String PATH_BASE_APACHE = "http://127.0.0.1/";
    public static String IMG_UPLOAD = "imgupload/";
    public static String FILE_UPLOAD = "fileupload/";
    public static String PATH_IMG_UPLOAD = PAHT_BASE_UPLOAD + IMG_UPLOAD;
    public static String PATH_FILE_UPLOAD = PAHT_BASE_UPLOAD + FILE_UPLOAD;
    public static String URL_IMG_UPLOAD = PATH_BASE_APACHE + IMG_UPLOAD;
    public static String URL_FILE_UPLOAD = PATH_BASE_APACHE + FILE_UPLOAD;

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath.endsWith("/") ?
                (filePath + fileName) : (filePath + "/" + fileName));
        out.write(file);
        out.flush();
        out.close();
    }
}

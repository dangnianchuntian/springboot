package com.zhanghan.zhboot.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class UploadUtil {

    public static void saveLocal(MultipartFile file, String path) throws Exception {
        try {
            InputStream fileImgInputStream = file.getInputStream();
            File dest = new File(path);
            if (!dest.exists()) {
                dest.mkdirs();
            }
            File dest1 = new File(dest, "" + UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            byte[] bytes = new byte[1024];
            int len;
            OutputStream fileOutputStream = new FileOutputStream(dest1);
            while ((len = fileImgInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

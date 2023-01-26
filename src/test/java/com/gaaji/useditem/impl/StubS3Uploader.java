package com.gaaji.useditem.impl;

import com.gaaji.useditem.adaptor.S3Uploader;
import org.springframework.web.multipart.MultipartFile;

public class StubS3Uploader implements S3Uploader {

    @Override
    public String[] upload(MultipartFile[] multipartFile) {

        String[] s = new String[multipartFile.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = "url"+i;
        }
        return s;
    }
}

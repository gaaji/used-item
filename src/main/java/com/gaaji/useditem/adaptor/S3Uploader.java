package com.gaaji.useditem.adaptor;

import org.springframework.web.multipart.MultipartFile;

public interface S3Uploader {
    String[] upload(MultipartFile[] multipartFile);
}

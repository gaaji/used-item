package com.gaaji.useditem.controller;

import com.gaaji.useditem.applicationservice.UsedItemPicturesUploadService;
import com.gaaji.useditem.controller.dto.FileIndexRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UsedItemPicturesUploadController {


    private final UsedItemPicturesUploadService usedItemPicturesUploadService;

    @PostMapping("/posts/{postId}/images")
    public ResponseEntity<Void> createPictures(@RequestPart("file") MultipartFile[] multipartFiles, @PathVariable("postId") String postId){
        usedItemPicturesUploadService.createPictures(postId,multipartFiles);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/posts/{postId}/images")
    public ResponseEntity<Void> updatePictures(@RequestPart("file") MultipartFile[] multipartFiles, @PathVariable("postId") String postId,
    @RequestPart("fileIndex") FileIndexRequest index){
        log.info("index = {}", index);
        usedItemPicturesUploadService.createPictures(postId,multipartFiles, index.getIndexes());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

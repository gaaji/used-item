package com.gaaji.useditem.controller;

import com.gaaji.useditem.applicationservice.UsedItemPicturesCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UsedItemPicturesCreateController {


    private final UsedItemPicturesCreateService usedItemPicturesCreateService;

    @PostMapping("/posts/{postId}/images")
    public ResponseEntity<Void> createPictures(@RequestPart("file") MultipartFile[] multipartFiles, @PathVariable("postId") String postId){

        usedItemPicturesCreateService.createPictures(postId,multipartFiles);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

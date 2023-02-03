package com.gaaji.useditem.applicationservice;

import com.gaaji.useditem.adaptor.S3Uploader;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.BothSizeDoseNotMatchedException;
import com.gaaji.useditem.exception.NoSearchPostException;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPicturesUploadService {

    private final S3Uploader s3Uploader;
    private final UsedItemPostRepository usedItemPostRepository;


    public void createPictures(String postId, MultipartFile[] files) {

        List<UsedItemPicture> pictures = createUsedItemPictures(files);

        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow(NoSearchPostException::new);
        usedItemPost.addPictures(pictures);

    }


    public void createPictures(String postId, MultipartFile[] newFiles, int[] indexes) {

        validateBothSize(newFiles, indexes);

        List<UsedItemPicture> pictures = createUsedItemPictures(newFiles);
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow(NoSearchPostException::new);
        usedItemPost.addPictures(pictures,indexes);

    }

    private void validateBothSize(MultipartFile[] newFiles, int[] indexes) {
        if(newFiles.length != indexes.length)
            throw new BothSizeDoseNotMatchedException();
    }

    private List<UsedItemPicture> createUsedItemPictures(MultipartFile[] files) {
        String[] urls = uploadImages(files);
        return Arrays.stream(urls)
                .map(url -> UsedItemPicture.of(
                        UsedItemPictureId.of(usedItemPostRepository.nextId())
                        ,url))
                .toList();
    }

    private String[] uploadImages(MultipartFile[] files) {
        return s3Uploader.upload(files);
    }


}

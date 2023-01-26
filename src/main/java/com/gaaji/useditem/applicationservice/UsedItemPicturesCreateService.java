package com.gaaji.useditem.applicationservice;

import com.gaaji.useditem.adaptor.S3Uploader;
import com.gaaji.useditem.domain.UsedItemPicture;
import com.gaaji.useditem.domain.UsedItemPictureId;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.UsedItemPostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedItemPicturesCreateService {

    private final S3Uploader s3Uploader;
    private final UsedItemPostRepository usedItemPostRepository;


    public void createPictures(String postId, MultipartFile[] files) {

        List<UsedItemPicture> pictures = createUsedItemPictures(files);

        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();
        usedItemPost.addPictures(pictures);

    }

    private List<UsedItemPicture> createUsedItemPictures(MultipartFile[] files) {
        String[] urls = uploadImages(files);
        List<UsedItemPicture> pictures = new ArrayList<>();
        for (String url : urls) {
            pictures.add(UsedItemPicture.of(UsedItemPictureId.of(usedItemPostRepository.nextId()), url));
        }
        return pictures;
    }

    private String[] uploadImages(MultipartFile[] files) {
        return s3Uploader.upload(files);
    }
}

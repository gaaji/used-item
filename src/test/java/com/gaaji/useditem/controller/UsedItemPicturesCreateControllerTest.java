package com.gaaji.useditem.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gaaji.useditem.applicationservice.UsedItemPicturesCreateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UsedItemPicturesCreateController.class)
class UsedItemPicturesCreateControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UsedItemPicturesCreateService usedItemPicturesCreateService;

    @InjectMocks
    UsedItemPicturesCreateController usedItemPicturesCreateController;

    @Test
    void 이미지_업로드_테스트() throws Exception {
        //given
//        given()
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "imagefile.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes());
        MockMultipartFile mockMultipartFile2 = new MockMultipartFile("file", "imagefile.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes());
        //when //then
        mockMvc.perform(multipart("/posts/foobar/images")
                        .file(mockMultipartFile)
                        .file(mockMultipartFile2)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andDo(print());

    }

}
package com.gaaji.useditem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.applicationservice.UsedItemPicturesUploadService;
import com.gaaji.useditem.controller.dto.FileIndexRequest;
import com.gaaji.useditem.exception.BothSizeDoseNotMatchedException;
import com.gaaji.useditem.exception.UsedItemExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@WebMvcTest(controllers = {UsedItemPicturesUploadController.class, UsedItemExceptionHandler.class})
class UsedItemPicturesUploadControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UsedItemPicturesUploadService usedItemPicturesUploadService;

    @InjectMocks
    UsedItemPicturesUploadController usedItemPicturesUploadController;

    @Test
    void 정상_이미지_업로드_생성_테스트() throws Exception {
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

    @Test
    void 정상_이미지_업로드_수정_테스트 () throws Exception{
        //given
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "imagefile.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes());

        FileIndexRequest fileIndexRequest = new FileIndexRequest(new int[]{2});
        byte[] indexBytes = new ObjectMapper().writeValueAsString(fileIndexRequest).getBytes();

        MockMultipartFile json = new MockMultipartFile("fileIndex", "fileIndex",
                "application/json",indexBytes);

        //when //then

        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/posts/foobar/images");
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PATCH");
                return request;
            }
        });

        mockMvc.perform(builder
                        .file(mockMultipartFile)
                        .file(json)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 예외_이미지_업로드_수정_파일_갯수_다름 () throws Exception{
        //given
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "imagefile.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes());

        FileIndexRequest fileIndexRequest = new FileIndexRequest(new int[]{2,3,4,5});
        byte[] indexBytes = new ObjectMapper().writeValueAsString(fileIndexRequest).getBytes();

        MockMultipartFile json = new MockMultipartFile("fileIndex", "fileIndex",
                "application/json",indexBytes);

        doThrow(new BothSizeDoseNotMatchedException())
                .when(usedItemPicturesUploadService)
                .createPictures(anyString(), any(), any());

        //when //then

        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/posts/foobar/images");
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PATCH");
                return request;
            }
        });

        mockMvc.perform(builder
                        .file(mockMultipartFile)
                        .file(json)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorCode").value("u-0014"))
                .andExpect(jsonPath("$.errorName").value("BOTH_SIZE_DOSE_NOT_MATCHED"))
                .andExpect(jsonPath("$.errorMessage").value("인덱스의 개수와 파일의 개수가 맞지 않습니다."))
                .andExpect(jsonPath("$.path").value("/posts/foobar/images"))
                .andDo(print());
    }

    @Test
    void 예외_이미지_업로드_수정_인덱스_초과 () throws Exception{
        //given
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "imagefile.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes());

        FileIndexRequest fileIndexRequest = new FileIndexRequest(new int[]{2,3,4,5});
        byte[] indexBytes = new ObjectMapper().writeValueAsString(fileIndexRequest).getBytes();

        MockMultipartFile json = new MockMultipartFile("fileIndex", "fileIndex",
                "application/json",indexBytes);

        doThrow(new IndexOutOfBoundsException())
                .when(usedItemPicturesUploadService)
                .createPictures(anyString(), any(), any());

        //when //then

        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/posts/foobar/images");
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PATCH");
                return request;
            }
        });

        mockMvc.perform(builder
                        .file(mockMultipartFile)
                        .file(json)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorCode").value("u-0015"))
                .andExpect(jsonPath("$.errorName").value("INDEX_OUT_OF_BOUNDS"))
                .andExpect(jsonPath("$.errorMessage").value("인덱스가 범위를 초과했습니다."))
                .andExpect(jsonPath("$.path").value("/posts/foobar/images"))
                .andDo(print());
    }

}
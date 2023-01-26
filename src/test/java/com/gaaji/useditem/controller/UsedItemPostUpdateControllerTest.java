package com.gaaji.useditem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.applicationservice.UsedItemPostUpdateService;
import com.gaaji.useditem.controller.dto.PostUpdateRequest;
import com.gaaji.useditem.exception.NoMatchAuthIdAndSellerIdException;
import com.gaaji.useditem.exception.ReservationStatusChangePriceException;
import com.gaaji.useditem.exception.UsedItemExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {UsedItemPostUpdateController.class, UsedItemExceptionHandler.class})
class UsedItemPostUpdateControllerTest {


    @Autowired
    MockMvc mvc;
    @InjectMocks
    UsedItemPostUpdateController usedItemPostUpdateController;

    @MockBean
    UsedItemPostUpdateService usedItemPostUpdateService;

    @Test
    void 정상_수정() throws Exception {
        //given
        List<String> urls = new ArrayList<>();
        urls.add("foo");
        urls.add("bar");
        urls.add("foobar");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);

        //when
        mvc.perform(patch("/posts/foo")
                        .header(HttpHeaders.AUTHORIZATION, "foo")
                        .header("X-TOWN-TOKEN", "bar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                //then
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    void 예외_아이디가_다르다() throws Exception {
        //given

        List<String> urls = new ArrayList<>();
        urls.add("foo");
        urls.add("bar");
        urls.add("foobar");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);
        doThrow(new NoMatchAuthIdAndSellerIdException())
                .when(usedItemPostUpdateService).updatePost(anyString(), anyString(), any());

        //when
        mvc.perform(patch("/posts/foo")
                        .header(HttpHeaders.AUTHORIZATION, "foo")
                        .header("X-TOWN-TOKEN", "bar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.httpStatus").value("UNAUTHORIZED"))
                .andExpect(jsonPath("$.errorCode").value("u-0009"))
                .andExpect(jsonPath("$.errorName").value("NO_MATCH_AUTH_ID_AND_SELLER_ID"))
                .andExpect(jsonPath("$.errorMessage").value("유저 정보(Id)가 작성자 정보(Id)와 일치하지 않습니다."))
                .andExpect(jsonPath("$.path").value("/posts/foo"))
                .andDo(print());
    }

    @Test
    void 예외_에약_상태에서_가격_변경() throws Exception {
        //given

        List<String> urls = new ArrayList<>();
        urls.add("foo");
        urls.add("bar");
        urls.add("foobar");
        PostUpdateRequest dto = new PostUpdateRequest("title", "content", "category", 10000, false, false, "","","", urls);
        doThrow(new ReservationStatusChangePriceException())
                .when(usedItemPostUpdateService).updatePost(anyString(), anyString(), any());

        //when
        mvc.perform(patch("/posts/foo")
                        .header(HttpHeaders.AUTHORIZATION, "foo")
                        .header("X-TOWN-TOKEN", "bar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorCode").value("u-0013"))
                .andExpect(jsonPath("$.errorName").value("RESERVATION_STATUS_CHANGE_PRICE"))
                .andExpect(jsonPath("$.errorMessage").value("예약 중엔 가격을 변경할 수 없습니다."))
                .andExpect(jsonPath("$.path").value("/posts/foo"))
                .andDo(print());
    }

}

package com.gaaji.useditem.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gaaji.useditem.adaptor.RetrieveResponse;
import com.gaaji.useditem.applicationservice.UsedItemPostRetrieveService;
import com.gaaji.useditem.controller.dto.PostRetrieveResponse;
import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.Post;
import com.gaaji.useditem.domain.Price;
import com.gaaji.useditem.domain.SellerId;
import com.gaaji.useditem.domain.Town;
import com.gaaji.useditem.domain.TradeStatus;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.domain.WishPlace;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = UsedItemPostRetrieveController.class)
class UsedItemPostRetrieveControllerTest {


    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    UsedItemPostRetrieveController usedItemPostRetrieveController;

    @MockBean
    UsedItemPostRetrieveService usedItemPostRetrieveService;

    @Test
    void 정상_글내용보기() throws Exception {
        //given
        given(usedItemPostRetrieveService.retrievePost(anyString(), anyString()))
                .willReturn(PostRetrieveResponse.of(UsedItemPost.of(UsedItemPostId.of("foo"),
                        SellerId.of("foo"), Post.of("foo","bar","foobar")
                , Price.of(10000000L), true, WishPlace.of("","","")
                , Town.of("foo","bar")), UsedItemPostCounter.of(UsedItemPostId.of("foo"), Counter.of())
                , new RetrieveResponse("foo", "익명","foo",36.5), "foo"));

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/123")
                        .header(HttpHeaders.AUTHORIZATION, "foo")
                        .header("X-TOWN-TOKEN", "bar"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value("foo"))
                .andExpect(jsonPath("$.viewCount").value(0))
                .andExpect(jsonPath("$.chatCount").value(0))
                .andExpect(jsonPath("$.interestCount").value(0))
                .andExpect(jsonPath("$.suggestCount").value(0))
                .andExpect(jsonPath("$.title").value("foo"))
                .andExpect(jsonPath("$.contents").value("bar"))
                .andExpect(jsonPath("$.category").value("foobar"))
                .andExpect(jsonPath("$.isHide").value(false))
                .andExpect(jsonPath("$.price").value(10000000))
                .andExpect(jsonPath("$.canSuggest").value(true))
                .andExpect(jsonPath("$.wishX").value(""))
                .andExpect(jsonPath("$.wishY").value(""))
                .andExpect(jsonPath("$.wishText").value(""))
                .andExpect(jsonPath("$.townId").value("foo"))
                .andExpect(jsonPath("$.townAddress").value("bar"))
                .andExpect(jsonPath("$.sellerId").value("foo"))
                .andExpect(jsonPath("$.sellerNickname").value("익명"))
                .andExpect(jsonPath("$.sellerMannerTemperature").value(36.5))
                .andExpect(jsonPath("$.isMine").value(true))
                .andExpect(jsonPath("$.tradeStatus").value(TradeStatus.SELLING.name()))
                .andExpect(jsonPath("$.sellerProfilePictureUrl").value("foo"))
                .andDo(print());





    }


}

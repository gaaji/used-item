package com.gaaji.useditem.domain;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.applicationservice.UsedItemPostCreateService;
import com.gaaji.useditem.controller.UsedItemPostCreateController;
import com.gaaji.useditem.controller.dto.PostCreateRequest;
import com.gaaji.useditem.controller.dto.TownToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UsedItemPostCreateController.class)
class UsedItemPostCreateControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UsedItemPostCreateService usedItemPostCreateService;

    @InjectMocks
    UsedItemPostCreateController usedItemPostCreateController;
    
    
    @Test
    void 정상생성 () throws Exception{
        //given
        BDDMockito.given(usedItemPostCreateService
                .createUsedItemPost(ArgumentMatchers.any(), anyString(), anyString()))
                .willReturn("foo");
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        TownToken townToken = new TownToken("foo",true);


        //when

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .header(HttpHeaders.AUTHORIZATION, "authorization")
                .header("X-TOWN-TOKEN", new ObjectMapper().writeValueAsString(townToken))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)));
        //then
            result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postId",is("foo")))
                .andDo(MockMvcResultHandlers.print());
    }

}

package com.gaaji.useditem.service;

import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.useditem.adaptor.TownServiceClient;
import com.gaaji.useditem.applicationservice.UsedItemPostCreateService;
import com.gaaji.useditem.controller.dto.PostCreateRequest;
import com.gaaji.useditem.controller.dto.TownToken;
import com.gaaji.useditem.domain.UsedItemPost;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.exception.InputNullDataOnAddressException;
import com.gaaji.useditem.exception.InputNullDataOnCategoryException;
import com.gaaji.useditem.exception.InputNullDataOnPriceException;
import com.gaaji.useditem.exception.InputNullDataOnSellerIdException;
import com.gaaji.useditem.exception.InputNullDataOnTitleException;
import com.gaaji.useditem.exception.InputNullDataOnTownIdException;
import com.gaaji.useditem.exception.JsonParsingException;
import com.gaaji.useditem.exception.TownUnAuthentiactedException;
import com.gaaji.useditem.impl.FakeUsedItemPostCounterRepository;
import com.gaaji.useditem.impl.FakeUsedItemPostRepository;
import com.gaaji.useditem.impl.StubNullTownServiceClient;
import com.gaaji.useditem.impl.StubTownServiceClient;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import org.junit.jupiter.api.Test;

class UsedItemPostCreateServiceTest {


    @Test
    void 정상_생성 () throws Exception{
        //given

        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",true);
        //when
        String postId = service.createUsedItemPost(dto, "foo",  new ObjectMapper().writeValueAsString(townToken));
        UsedItemPost usedItemPost = usedItemPostRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();

        UsedItemPostCounter usedItemPostCounter = usedItemPostCounterRepository.findByPostId(UsedItemPostId.of(postId))
                .orElseThrow();

        //then
        assertThat(usedItemPost).isNotNull();
        assertThat(usedItemPostCounter).isNotNull();
        assertThat(usedItemPost.getUsedItemPostId()).isEqualTo(postId);
        assertThat(usedItemPostCounter.getUsedItemPostIdToString()).isEqualTo(postId);
    }

    @Test
    void 예외_인증X () throws Exception{
        //given

        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",false);
        //when
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "foo",  new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(TownUnAuthentiactedException.class);

    }

    @Test
    void 예외_파싱에러X () throws Exception{
        //given

        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);

        //when
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "foo",  "bar"))
                .isInstanceOf(JsonParsingException.class);

    }



    @Test
    void 예외_제목X () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",true);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "foo",  new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnTitleException.class);
    }
    @Test
    void 예외_카테고리X () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",true);

        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "foo",   new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnCategoryException.class);
    
    }
    
    @Test
    void 예외_가격X () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,null, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",true);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "foo",   new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnPriceException.class);
    }

    @Test
    void 예외_가격_음수 () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,-2000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",true);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto,"foo", new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnPriceException.class);
    }


    @Test
    void 예외_판매자ID () throws Exception{
        //given
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken("foo",true);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "", new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnSellerIdException.class);
    }
    @Test
    void 예외_동네ID () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken();
        townToken.setAuthenticated(true);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto, "foo", new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnTownIdException.class);
    }
    @Test
    void 예외_동네주소 () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null);

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        TownServiceClient townServiceClient = new StubNullTownServiceClient();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository,townServiceClient);
        TownToken townToken = new TownToken();
        townToken.setTownId("foo");
        townToken.setAuthenticated(true);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto,"foo", new ObjectMapper().writeValueAsString(townToken)))
                .isInstanceOf(InputNullDataOnAddressException.class);
    }



}

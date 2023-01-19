package com.gaaji.useditem.domain;

import static org.assertj.core.api.Assertions.*;

import com.gaaji.useditem.applicationservice.UsedItemPostCreateService;
import com.gaaji.useditem.controller.dto.PostCreateRequest;
import com.gaaji.useditem.exception.InputNullDataOnAddressException;
import com.gaaji.useditem.exception.InputNullDataOnCategoryException;
import com.gaaji.useditem.exception.InputNullDataOnPriceException;
import com.gaaji.useditem.exception.InputNullDataOnSellerIdException;
import com.gaaji.useditem.exception.InputNullDataOnTitleException;
import com.gaaji.useditem.exception.InputNullDataOnTownIdException;
import com.gaaji.useditem.repository.UsedItemPostCounterRepository;
import com.gaaji.useditem.repository.UsedItemPostRepository;

import org.junit.jupiter.api.Test;

class UsedItemPostCreateServiceTest {


    @Test
    void 정상_생성 () throws Exception{
        //given

        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null
                ,"foo","bar","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);

        //when
        String postId = service.createUsedItemPost(dto);
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
    void 예외_제목X () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("","contents","category",true,1000L, null,null,null
                ,"foo","bar","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);

        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnTitleException.class);
    }
    @Test
    void 예외_카테고리X () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","",true,1000L, null,null,null
                ,"foo","bar","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);

        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnCategoryException.class);
    
    }
    
    @Test
    void 예외_가격X () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,null, null,null,null
                ,"foo","bar","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);

        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnPriceException.class);
    }

    @Test
    void 예외_가격_음수 () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,-2000L, null,null,null
                ,"foo","bar","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);

        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnPriceException.class);
    }


    @Test
    void 예외_판매자ID () throws Exception{
        //given
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null
                ,"","bar","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnSellerIdException.class);
    }
    @Test
    void 예외_동네ID () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null
                ,"foo","","foobar");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnTownIdException.class);
    }
    @Test
    void 예외_동네주소 () throws Exception{
        PostCreateRequest dto = new PostCreateRequest("title","contents","category",true,1000L, null,null,null
                ,"foo","bar","");

        UsedItemPostRepository usedItemPostRepository = new FakeUsedItemPostRepository();
        UsedItemPostCounterRepository usedItemPostCounterRepository = new FakeUsedItemPostCounterRepository();
        UsedItemPostCreateService service = new UsedItemPostCreateService(usedItemPostRepository, usedItemPostCounterRepository);
        //when // then
        assertThatThrownBy(() -> service.createUsedItemPost(dto))
                .isInstanceOf(InputNullDataOnAddressException.class);
    }



}

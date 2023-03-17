package com.gaaji.useditem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gaaji.useditem.domain.Counter;
import com.gaaji.useditem.domain.UsedItemPostCounter;
import com.gaaji.useditem.domain.UsedItemPostId;
import com.gaaji.useditem.repository.JpaUsedItemPostCounterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UsedItemPostCounterRepositoryJpaTest {

    @Autowired
    JpaUsedItemPostCounterRepository jpaUsedItemPostCounterRepository;
    
    
    @Test
    void 저장_조회 () throws Exception{
        //given
        String expected = "foo";
        UsedItemPostCounter save = jpaUsedItemPostCounterRepository.save(
                UsedItemPostCounter.of(UsedItemPostId.of(expected), Counter.of()));
        //when
        UsedItemPostCounter finded = jpaUsedItemPostCounterRepository.findById(
                        UsedItemPostId.of(expected))
                .orElseThrow();

        //then
        assertThat(finded.getUsedItemPostIdToString()).isEqualTo(expected);
        assertThat(save).isEqualTo(finded);
        
    
    }
}

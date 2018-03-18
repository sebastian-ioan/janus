package com.example.janus.repository;

import com.example.janus.model.Ciupici;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.Serializable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class CiupiciRepositoryTest {

    @Autowired
    private CiupiciRepository ciupiciRepository;


    @Test
    void save() {

        final Ciupici ciupici = Ciupici.builder().name("Jiji").build();
        final Serializable id = ciupiciRepository.save(ciupici);
        assertThat(id, notNullValue());
    }

    @Test
    void findById() {
    }
}
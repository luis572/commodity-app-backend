package com.nexos.storagesystem.controller.position;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.service.position.PositionService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PositionControllerTest {

    private MockMvc mockMvc;

    private Faker faker = new Faker();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PositionService positionService;

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void created() throws Exception {
        PositionRequest positionRequest  = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/positions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(positionRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(positionRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(positionRequest.uuid.toString())

                        )
                );
    }

    @Test
     void show() throws Exception{
        PositionRequest positionRequest  = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/positions/"+positionRequest.uuid.toString())
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(positionRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(positionRequest.uuid.toString())

                        )
                );

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/positions/"+UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isNotFound()
                        )
                );
    }
}

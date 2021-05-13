package com.nexos.storagesystem.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.dto.user.v1.UserRequest;
import com.nexos.storagesystem.service.position.PositionService;
import com.nexos.storagesystem.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
class UserControllerTest {

    private MockMvc mockMvc;

    private Faker faker = new Faker();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PositionService positionService;

    @Autowired
    private UserService userService;

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
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age =  Integer.parseInt(faker.number().digit());

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(userRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(userRequest.uuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.position.name").value(positionRequest.name),
                                MockMvcResultMatchers.jsonPath("$.last_modified_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.created_at").isNotEmpty())

                        );
    }

    @Test
     void show() throws Exception{
        PositionRequest positionRequest  = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age =  Integer.parseInt(faker.number().digit());

        userService.create(userRequest);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/users/"+userRequest.uuid.toString())
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(userRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(userRequest.uuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.position.name").value(positionRequest.name),
                                MockMvcResultMatchers.jsonPath("$.last_modified_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.created_at").isNotEmpty())
                        );

    }
}

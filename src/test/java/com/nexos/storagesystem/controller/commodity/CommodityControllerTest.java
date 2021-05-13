package com.nexos.storagesystem.controller.commodity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.nexos.storagesystem.dto.commodity.v1.CommodityDto;
import com.nexos.storagesystem.dto.commodity.v1.CommodityRequest;
import com.nexos.storagesystem.dto.position.v1.PositionRequest;
import com.nexos.storagesystem.dto.user.v1.UserDto;
import com.nexos.storagesystem.dto.user.v1.UserRequest;
import com.nexos.storagesystem.model.Commodity;
import com.nexos.storagesystem.service.commodity.CommodityService;
import com.nexos.storagesystem.service.position.PositionService;
import com.nexos.storagesystem.service.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
class CommodityControllerTest {

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

    @Autowired
    private CommodityService commodityService;

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void created() throws Exception {
        PositionRequest positionRequest = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age = Integer.parseInt(faker.number().digit());

        userService.create(userRequest);

        CommodityRequest commodityRequest = new CommodityRequest();
        commodityRequest.uuid = UUID.randomUUID();
        commodityRequest.name = faker.name().fullName();
        commodityRequest.amount = Integer.parseInt(faker.number().digit());
        commodityRequest.userUuid = userRequest.uuid;

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/commodities")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commodityRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(commodityRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(commodityRequest.uuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.last_modified_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.created_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.user_uuid").value(commodityRequest.userUuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.amount").value(commodityRequest.amount))
                );

        commodityRequest.userUuid = UUID.randomUUID();
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/commodities")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commodityRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isBadRequest())
                );

        commodityRequest.uuid = UUID.randomUUID();
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/commodities")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commodityRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isBadRequest())
                );
    }

    @Test
    void update() throws Exception {

        PositionRequest positionRequest = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age = Integer.parseInt(faker.number().digit());

        userService.create(userRequest);

        CommodityRequest commodityRequest = new CommodityRequest();
        commodityRequest.uuid = UUID.randomUUID();
        commodityRequest.name = faker.name().fullName();
        commodityRequest.amount = Integer.parseInt(faker.number().digit());
        commodityRequest.userUuid = userRequest.uuid;

        commodityService.create(commodityRequest);

        commodityRequest.amount = 5;

        this.mockMvc.perform(
                MockMvcRequestBuilders.patch("/v1/commodities/" + commodityRequest.uuid)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commodityRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(commodityRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(commodityRequest.uuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.last_modified_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.created_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.user_uuid").value(commodityRequest.userUuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.amount").value(5))
                );
        this.mockMvc.perform(
                MockMvcRequestBuilders.patch("/v1/commodities/" + UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commodityRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isNotFound())
                );

    }

    @Test
    void index() throws Exception {

        PositionRequest positionRequest = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age = Integer.parseInt(faker.number().digit());
        userService.create(userRequest);

        for (int i = 1; i <= 10; i++) {
            CommodityRequest commodityRequest = new CommodityRequest();
            commodityRequest.uuid = UUID.randomUUID();
            commodityRequest.name = faker.name().fullName();
            commodityRequest.amount = Integer.parseInt(faker.number().digit());
            commodityRequest.userUuid = userRequest.uuid;
            commodityService.create(commodityRequest);
        }
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/commodities")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk()
                        ));

    }

    @Test
    void indexWhitFilters() throws Exception {


        PositionRequest positionRequest = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age = Integer.parseInt(faker.number().digit());
        userService.create(userRequest);

        for (int i = 1; i <= 10; i++) {
            CommodityRequest commodityRequest = new CommodityRequest();
            commodityRequest.uuid = UUID.randomUUID();
            commodityRequest.name = "test" + Integer.toString(i);
            commodityRequest.amount = Integer.parseInt(faker.number().digit());
            commodityRequest.userUuid = userRequest.uuid;
            commodityService.create(commodityRequest);
        }
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/commodities?search=name:test5")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.jsonPath("$.number_of_elements").value(1)
                        ));


    }

    @Test
    void show() throws Exception {
        PositionRequest positionRequest = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UserRequest userRequest = new UserRequest();
        userRequest.uuid = UUID.randomUUID();
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age = Integer.parseInt(faker.number().digit());

        userService.create(userRequest);

        CommodityRequest commodityRequest = new CommodityRequest();
        commodityRequest.uuid = UUID.randomUUID();
        commodityRequest.name = faker.name().fullName();
        commodityRequest.amount = Integer.parseInt(faker.number().digit());
        commodityRequest.userUuid = userRequest.uuid;

        commodityService.create(commodityRequest);

        this.mockMvc.perform(
                MockMvcRequestBuilders.patch("/v1/commodities/" + commodityRequest.uuid)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commodityRequest))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk(),
                                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                                MockMvcResultMatchers.jsonPath("$.name").value(commodityRequest.name),
                                MockMvcResultMatchers.jsonPath("$.uuid").value(commodityRequest.uuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.last_modified_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.created_at").isNotEmpty(),
                                MockMvcResultMatchers.jsonPath("$.user_uuid").value(commodityRequest.userUuid.toString()),
                                MockMvcResultMatchers.jsonPath("$.amount").value(commodityRequest.amount))
                );

    }

    @Test
    void delete() throws Exception {
        PositionRequest positionRequest = new PositionRequest();
        positionRequest.name = faker.name().fullName();
        positionRequest.uuid = UUID.randomUUID();
        positionService.create(positionRequest);

        UUID userUuid = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        userRequest.uuid = userUuid;
        userRequest.name = faker.name().fullName();
        userRequest.positionUuid = positionRequest.uuid;
        userRequest.age = Integer.parseInt(faker.number().digit());

        userService.create(userRequest);

        CommodityRequest commodityRequest = new CommodityRequest();
        commodityRequest.uuid = UUID.randomUUID();
        commodityRequest.name = faker.name().fullName();
        commodityRequest.amount = Integer.parseInt(faker.number().digit());
        commodityRequest.userUuid = userUuid;

       commodityService.create(commodityRequest);


        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/commodities/" + commodityRequest.uuid)
                        .header("user-uuid", UUID.randomUUID())
                        .accept(MediaType.TEXT_PLAIN)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isForbidden()
                        ));

        this.mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/commodities/" + commodityRequest.uuid)
                        .header("user-uuid", userUuid)
                        .accept(MediaType.TEXT_PLAIN)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        ResultMatcher.matchAll(
                                MockMvcResultMatchers.status().isOk()
                        ));

    }


}

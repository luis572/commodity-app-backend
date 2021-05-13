package com.nexos.storagesystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@EnableJpaAuditing
class DemoApplicationTests { @Test void contextLoads() { }}

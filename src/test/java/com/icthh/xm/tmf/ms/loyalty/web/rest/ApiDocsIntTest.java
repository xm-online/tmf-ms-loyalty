package com.icthh.xm.tmf.ms.loyalty.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.icthh.xm.tmf.ms.loyalty.LoyaltyApp;
import com.icthh.xm.tmf.ms.loyalty.config.SecurityBeanOverrideConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Guards the OpenAPI document springdoc serves at /v3/api-docs - the endpoint the gateway and
 * Swagger UI read. Nothing else in the suite touches it, so a broken springdoc setup used to go
 * unnoticed until runtime.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, LoyaltyApp.class})
public class ApiDocsIntTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void apiDocsIsServedAsOpenApi3() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.openapi").value(org.hamcrest.Matchers.startsWith("3.")))
            .andExpect(jsonPath("$.paths").isNotEmpty());
    }
}

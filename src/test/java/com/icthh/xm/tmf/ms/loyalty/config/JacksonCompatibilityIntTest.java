package com.icthh.xm.tmf.ms.loyalty.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.icthh.xm.tmf.ms.loyalty.LoyaltyApp;
import com.icthh.xm.tmf.ms.loyalty.web.api.model.ProductProgramRef;
import com.icthh.xm.tmf.ms.loyalty.web.rest.errors.FieldErrorVM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/**
 * Request and response JSON must stay as it was with Jackson 2 / openapi-generator 4.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, LoyaltyApp.class})
public class JacksonCompatibilityIntTest {

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    public void modelPropertiesKeepDeclarationOrderAndNullCollections() {
        ProductProgramRef model = jsonMapper.readValue("{\"name\":\"n\",\"productSpecId\":\"p\"}", ProductProgramRef.class);
        model.setId("1");

        JsonNode json = jsonMapper.readTree(jsonMapper.writeValueAsString(model));

        assertThat(json.propertyNames()).containsExactly("id", "name", "description", "productStatus", "validFor",
            "productSpecId", "accountId", "loyaltyAccount", "characteristics");
        assertThat(json.get("characteristics").isNull()).isTrue();
    }

    @Test
    public void fieldErrorKeepsPropertyOrder() {
        JsonNode json = jsonMapper.readTree(jsonMapper.writeValueAsString(new FieldErrorVM("dto", "field", "NotNull")));

        assertThat(json.propertyNames()).containsExactly("objectName", "field", "message");
    }
}

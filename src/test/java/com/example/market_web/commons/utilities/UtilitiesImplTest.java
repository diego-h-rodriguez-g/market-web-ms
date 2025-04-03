package com.example.market_web.commons.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UtilitiesImplTest {

    @InjectMocks
    private UtilitiesImpl utilities;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a String in method convertObjectToString")
    public void returnStringInConvertObjectToString() throws JsonProcessingException {
        Object object = new Object();

        when(objectMapper.writeValueAsString(object)).thenReturn("{}");

        assertThat(utilities.convertObjectToString(object), instanceOf(String.class));
    }

    @Test
    @DisplayName("Should return a Null in method convertObjectToString")
    public void returnNullInConvertObjectToString() throws JsonProcessingException {
        Object inputObject = new Object();

        when(objectMapper.writeValueAsString(inputObject)).thenThrow(JsonProcessingException.class);

        assertNull(utilities.convertObjectToString(inputObject));
    }

    @Test
    @DisplayName("Should return a Sort in method buildSort when order ASC")
    public void returnSortInBuildSortWhenOrderAsc() {
        String field = "field";
        Boolean isAsc = true;

        Sort receivedResponse = utilities.buildSort(field, isAsc);

        Assertions.assertEquals(Sort.Direction.ASC, Objects.requireNonNull(receivedResponse.getOrderFor(field)).getDirection());
    }

    @Test
    @DisplayName("Should return a Sort in method buildSort when order DESC")
    public void returnSortInBuildSortWhenOrderDesc() {
        String field = "field";
        Boolean isAsc = false;

        Sort receivedResponse = utilities.buildSort(field, isAsc);

        Assertions.assertEquals(Sort.Direction.DESC, Objects.requireNonNull(receivedResponse.getOrderFor(field)).getDirection());
    }

    @Test
    @DisplayName("Should return a Boolean in method isNullObject")
    public void returnBooleanInIsNullObject() {
        assertFalse(utilities.objectIsNull(new Object()));
        assertTrue(utilities.objectIsNull(null));
    }
}

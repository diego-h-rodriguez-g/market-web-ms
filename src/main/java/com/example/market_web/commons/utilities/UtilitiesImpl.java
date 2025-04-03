package com.example.market_web.commons.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class UtilitiesImpl implements Utilities {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(String.valueOf(UtilitiesImpl.class));

    private final ObjectMapper objectMapper;

    public UtilitiesImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertObjectToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception exception) {
            log.log(Level.INFO, "An uncaught exception has occurred: {0}", new Object[]{exception});
        }
        return null;
    }

    @Override
    public Sort buildSort(String field, Boolean isAsc) {
        if (Boolean.TRUE.equals(isAsc)) {
            return Sort.by(field).ascending();
        }
        return Sort.by(field).descending();
    }
}

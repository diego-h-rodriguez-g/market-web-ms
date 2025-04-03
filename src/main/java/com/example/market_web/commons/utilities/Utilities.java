package com.example.market_web.commons.utilities;

import org.springframework.data.domain.Sort;

public interface Utilities {

    String convertObjectToString(Object object);

    Sort buildSort(String field, Boolean isAsc);

    Boolean objectIsNull(Object object);
}

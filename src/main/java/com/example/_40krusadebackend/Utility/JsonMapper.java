package com.example._40krusadebackend.Utility;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <S, D> D map(S source, Class<D> destinationClass) {
        return objectMapper.convertValue(source, destinationClass);
    }
}

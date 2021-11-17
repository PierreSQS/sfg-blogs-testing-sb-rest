package com.pierrot.sfgtestingspringboot.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest {
    static String writeAsJsonValue(Object object) {
        String jsonValue= null;
        try {
            jsonValue = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }
}

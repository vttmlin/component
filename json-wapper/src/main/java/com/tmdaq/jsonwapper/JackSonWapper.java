package com.tmdaq.jsonwapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public class JackSonWapper extends Json {
    private static ObjectMapper holder = new ObjectMapper();

    @Override
    public Map readValue(String json) {
        if (json != null && !"".equals(json)) {
            try {
                return holder.readValue(json, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>(0);
    }

    @Override
    public List readValueFromList(String json) {
        if (json != null && !"".equals(json)) {
            try {
                return holder.readValue(json, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }

    @Override
    public String toJsonString(Object o) {
        try {
            return holder.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}

package com.tmdaq.component.json.jsonwapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tmdaq.component.json.jsonwapper.Json.Result.ERROR;

/**
 * @author vttmlin
 */
@Slf4j
public class JackSonWapper implements Json {
    private static ObjectMapper holder = new ObjectMapper();

    @Override
    public Map readValue(String json) {
        if (json != null && !"".equals(json)) {
            try {
                return holder.readValue(json, Map.class);
            } catch (IOException e) {
                log.error(ERROR.getMsg(), e);
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
                log.error(ERROR.getMsg(), e);
            }
        }
        return new ArrayList();
    }

    @Override
    public String toJsonString(Object o) {
        try {
            return holder.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error(ERROR.getMsg(), e);
        }
        return "";
    }
}

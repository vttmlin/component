package com.tmdaq.component.json.jsonwapper;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tmdaq.component.json.jsonwapper.Json.Result.ERROR;

/**
 * @author vttmlin
 */
@Slf4j
public class JsonWapper implements Json {

    @Override
    public Map readValue(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new HashMap(0);
        } else {
            return new JSONObject(json).toMap();
        }
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new ArrayList();
        }
        return new JSONArray(json).toList();
    }

    @Override
    public String toJsonString(Object o) {
        if (o instanceof List) {
            return new JSONArray(o).toString();
        } else {
            return new JSONObject(o).toString();
        }
    }
}

package com.tmdaq.component.json.jsonwapper;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tmdaq.component.json.jsonwapper.Json.Result.ERROR;

/**
 * @author vttmlin
 */
@Slf4j
public class GsonWapper implements Json {
    private static Gson gson = new Gson();

    @Override
    public Map readValue(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new HashMap<>(0);
        }
        return gson.fromJson(json, Map.class);
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new ArrayList();
        }
        return gson.fromJson(json, List.class);
    }

    @Override
    public String toJsonString(Object o) {
        return gson.toJson(o);
    }


}

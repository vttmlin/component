package com.tmdaq.jsonwapper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public class GsonWapper extends Json {
    private static Gson gson = new Gson();

    @Override
    public Map readValue(String json) {
        if (json == null || "".equals(json)) {
            return new HashMap<>(0);
        }
        return gson.fromJson(json, Map.class);
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            return new ArrayList();
        }
        return gson.fromJson(json, List.class);
    }

    @Override
    public String toJsonString(Object o) {
        return gson.toJson(o);
    }


}

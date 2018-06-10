package com.tmdaq.jsonwapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public class JsonWapper extends Json {

    @Override
    public Map readValue(String json) {
        if (json == null || "".equals(json)) {
            return new HashMap(0);
        } else {
            return new JSONObject(json).toMap();
        }
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
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

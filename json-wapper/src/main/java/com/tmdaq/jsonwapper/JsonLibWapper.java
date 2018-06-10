package com.tmdaq.jsonwapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public class JsonLibWapper extends Json {
    @Override
    public Map readValue(String json) {
        if (json == null || "".equals(json)) {
            return new HashMap(0);
        }
        return JSONObject.fromObject(json);
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            return new ArrayList();
        }
        return JSONArray.fromObject(json);
    }

    @Override
    public String toJsonString(Object o) {
        if (o instanceof List) {
            return JSONArray.fromObject(o).toString();
        } else {
            return JSONObject.fromObject(o).toString();
        }
    }
}

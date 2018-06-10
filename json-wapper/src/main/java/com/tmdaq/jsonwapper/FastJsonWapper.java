package com.tmdaq.jsonwapper;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public class FastJsonWapper extends Json {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> readValue(String json) {
        if (json == null || "".equals(json)) {
            return new HashMap<>(0);
        }
        return JSON.parseObject(json, Map.class);
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            return new ArrayList();
        }
        return JSON.parseObject(json, List.class);
    }

    @Override
    public String toJsonString(Object o) {
        return JSON.toJSONString(o);
    }
}

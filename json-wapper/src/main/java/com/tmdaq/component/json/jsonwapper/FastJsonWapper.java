package com.tmdaq.component.json.jsonwapper;

import com.alibaba.fastjson.JSON;
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
public class FastJsonWapper implements Json {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> readValue(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new HashMap<>(0);
        }
        return JSON.parseObject(json, Map.class);
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new ArrayList();
        }
        return JSON.parseObject(json, List.class);
    }

    @Override
    public String toJsonString(Object o) {
        return JSON.toJSONString(o);
    }
}

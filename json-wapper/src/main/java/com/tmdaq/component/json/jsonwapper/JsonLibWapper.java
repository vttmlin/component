package com.tmdaq.component.json.jsonwapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tmdaq.component.json.jsonwapper.Json.Result.ERROR;

/**
 * @author vttmlin
 */
@Slf4j
public class JsonLibWapper implements Json {
    @Override
    public Map readValue(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
            return new HashMap(0);
        }
        return JSONObject.fromObject(json);
    }

    @Override
    public List readValueFromList(String json) {
        if (json == null || "".equals(json)) {
            log.error(ERROR.getMsg());
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

package com.tmdaq.jsonwapper;

import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public abstract class Json {

    /**
     * @param json json字符串
     * @return 返回解析后的map 永远不会返回null 只应该返回一个空的hashmap
     */
    public abstract Map readValue(String json);

    /**
     * @param json json字符串
     * @return 返回解析后的map 永远不会返回null 只应该返回一个空的List
     */
    public abstract List readValueFromList(String json);

    public abstract String toJsonString(Object o);
}

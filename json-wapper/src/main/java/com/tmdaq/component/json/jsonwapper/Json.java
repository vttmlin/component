package com.tmdaq.component.json.jsonwapper;

import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
public interface Json {

    /**
     * @param json json字符串
     * @return 返回解析后的map 永远不会返回null 只应该返回一个空的hashmap
     */
    Map readValue(String json);

    /**
     * @param json json字符串
     * @return 返回解析后的map 永远不会返回null 只应该返回一个空的List
     */
    List readValueFromList(String json);

    String toJsonString(Object o);

    enum Result {
        ERROR("JSON转换失败");
        private String msg;

        Result(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public Result setMsg(String msg) {
            this.msg = msg;
            return this;
        }
    }
}

package com.tmdaq.component.json;

import com.tmdaq.component.json.jsonwapper.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author vttmlin
 */
@Slf4j
public class JsonHolder {
    public static final Json instance = getInstance();

    private JsonHolder() {
    }

    public static final Json getInstance() {
        return Holder.instalce;
    }

    private static final class Holder {
        private static Json instalce = null;
        private static String[] arr = new String[]{"com.alibaba.fastjson.JSON", "com.google.gson.Gson", "com.fasterxml.jackson.databind.ObjectMapper", "net.sf.json.JSONObject", "org.json.JSONObject"};

        static {
            for (String s : arr) {
                if ("com.alibaba.fastjson.JSON".equals(s)) {
                    instalce = new FastJsonWapper();
                }
                if ("com.google.gson.Gson".equals(s)) {
                    instalce = new GsonWapper();
                }
                if ("com.fasterxml.jackson.databind.ObjectMapper".equals(s)) {
                    instalce = new JackSonWapper();
                }
                if ("net.sf.json.JSONObject".equals(s)) {
                    instalce = new JsonLibWapper();
                }
                if ("org.json.JSONObject".equals(s)) {
                    instalce = new JsonWapper();
                }
            }
        }
    }

}

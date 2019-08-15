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
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static Json INSTANCE = null;
        private static String[] arr = new String[]{"com.alibaba.fastjson.JSON", "com.google.gson.Gson", "com.fasterxml.jackson.databind.ObjectMapper", "net.sf.json.JSONObject", "org.json.JSONObject"};


        static {
            for (String s : arr) {
                try {
                    Class.forName(s);
                    if ("com.alibaba.fastjson.JSON".equals(s)) {
                        INSTANCE = new FastJsonWapper();
                    }
                    if ("com.google.gson.Gson".equals(s)) {
                        INSTANCE = new GsonWapper();
                    }
                    if ("com.fasterxml.jackson.databind.ObjectMapper".equals(s)) {
                        INSTANCE = new JackSonWapper();
                    }
                    if ("net.sf.json.JSONObject".equals(s)) {
                        INSTANCE = new JsonLibWapper();
                    }
                    if ("org.json.JSONObject".equals(s)) {
                        INSTANCE = new JsonWapper();
                    }
                    break;
                } catch (ClassNotFoundException e) {
                    //ignore error
                }

            }
        }
    }

}

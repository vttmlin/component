package com.tmdaq;

import com.tmdaq.jsonwapper.*;

public class JsonHolder {
    private static Json json;

    public JsonHolder() {
        String[] arr = new String[]{"com.alibaba.fastjson.JSON", "com.google.gson.Gson", "com.fasterxml.jackson.databind.ObjectMapper", "net.sf.json.JSONObject", "org.json.JSONObject"};
        synchronized (this) {
            if (json == null) {
                for (String s : arr) {
                    try {
                        Class<?> aClass = Class.forName(s);
                        load(aClass);
                        break;
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
        }
        if (json == null) {
            System.err.println("请选择json工具");
        }
    }

    private void load(Class<?> aClass) {
        if (aClass != null) {
            switch (aClass.getName()) {
                case "com.alibaba.fastjson.JSON":
                    json = new FastJsonWapper();
                    break;
                case "com.google.gson.Gson":
                    json = new GsonWapper();
                    break;
                case "com.fasterxml.jackson.databind.ObjectMapper":
                    json = new JackSonWapper();
                    break;
                case "net.sf.json.JSONObject":
                    json = new JsonLibWapper();
                    break;
                case "org.json.JSONObject":
                    json = new JsonWapper();
                    break;
                default:
                    break;
            }
        }
    }

    public Json getJson() {
        return json;
    }
}

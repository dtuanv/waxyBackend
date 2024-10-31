package com.waxy.utils;

import org.springframework.stereotype.Component;

@Component
public class EnvUtil {

    public String getEnv(String key) {
        return System.getenv(key);
        }
}

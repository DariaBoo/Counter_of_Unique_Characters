package com.foxminded.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private Map<String, Map<Character, Integer>> cache = new LinkedHashMap<>();

    public void saveCache(String key, Map<Character, Integer> value) {

        cache.put(key, value);
    }

    public Map<Character, Integer> getCache(String key) {

        return cache.get(key);
    }

    public boolean isContainsCache(String key) {

        return cache.containsKey(key);
    }
}

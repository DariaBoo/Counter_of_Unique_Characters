package com.foxminded.initialization;

import java.util.LinkedHashMap;
import java.util.Map;

import com.foxminded.cache.Cache;
import com.foxminded.counter.CharCounter;
import com.foxminded.formatter.Formatter;

public class Initialization {
    Cache cache = new Cache();
    CharCounter counter = new CharCounter();
    Formatter formatter = new Formatter();

    public String initCharCounting(String phrase) {
        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        if (cache.isContainsCache(phrase)) {
            resultMap = cache.getCache(phrase);
        } else {
            if (phrase == null) {
                throw new NullPointerException(" : Parameter 'phrase' was null inside the method countCharacters(phrase)");
            }
            resultMap = counter.countCharacters(phrase);
            cache.saveCache(phrase, resultMap);
        }
        return formatter.formattingResult(resultMap);
    }
}

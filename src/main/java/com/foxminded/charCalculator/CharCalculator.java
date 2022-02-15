package com.foxminded.charCalculator;

import java.util.LinkedHashMap;
import java.util.Map;

import com.foxminded.cache.Cache;
import com.foxminded.counter.Counter;
import com.foxminded.formatter.Formatter;

public class CharCalculator { 
    private Cache cache = new Cache();
    private Counter counter = new Counter();
    private Formatter formatter = new Formatter();

    public CharCalculator() {
    }

    public CharCalculator(Cache cache, Counter counter) {
        this.cache = cache;
        this.counter = counter;
    }

    public String initCharCounting(String phrase) {
        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        if (cache.isContainsCache(phrase)) {
            resultMap = cache.getCache(phrase);
        } else {
            if (phrase == null) {
                throw new NullPointerException(
                        " : Parameter 'phrase' was null inside the method countCharacters(phrase)");
            }
            resultMap = counter.countCharacters(phrase);
            cache.saveCache(phrase, resultMap);
        }
        return formatter.formattingResult(resultMap);
    }
}

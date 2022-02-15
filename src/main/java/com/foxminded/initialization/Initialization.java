package com.foxminded.initialization;

import java.util.LinkedHashMap;
import java.util.Map;

import com.foxminded.cache.Cache;
import com.foxminded.counter.CharCounter;
import com.foxminded.formatter.Formatter;

public class Initialization { //Меняем название класса. СharCalculator например.
    Cache cache = new Cache(); // private
    CharCounter counter = new CharCounter();// private
    Formatter formatter = new Formatter();// private


    public Initialization() {
    }

    public Initialization(Cache cache, CharCounter counter) {
        this.cache = cache;
        this.counter = counter;
    }

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

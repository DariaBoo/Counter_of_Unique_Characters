package com.foxminded.counter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {
    public Map<Character, Integer> countCharacters(String phrase) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] characters = phrase.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            if (map.containsKey(characters[i])) {
                int number = map.get(characters[i]);
                map.put(characters[i], ++number);
            } else {
                map.put(characters[i], 1);
            }
        }
        return map;
    }
}

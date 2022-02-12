package com.foxminded.formatter;

import java.util.Map;

public class Formatter {
    public String formattingResult(Map<Character, Integer> map) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> pair : map.entrySet()) {
            result.append("\"" + pair.getKey() + "\"" + " - " + pair.getValue()).append("\n");
        }
        return result.toString();
    }
}

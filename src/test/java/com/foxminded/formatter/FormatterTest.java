package com.foxminded.formatter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class FormatterTest {
    Formatter formatter = new Formatter();

    @Test
    void formattingResult_shouldReturnFormattingString_whenInputFilledMap() {
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        map.put('h', 1);
        map.put('e', 1);
        map.put('l', 3);
        map.put('o', 2);
        map.put(' ', 1);
        map.put('w', 1);
        map.put('r', 1);
        map.put('d', 1);
        String expected = "\"h\" - 1\n" + "\"e\" - 1\n" + "\"l\" - 3\n" + "\"o\" - 2\n" + "\" \" - 1\n" + "\"w\" - 1\n"
                + "\"r\" - 1\n" + "\"d\" - 1\n";
        assertEquals(expected, formatter.formattingResult(map));

    }

    @Test
    void formattingResult_shouldReturnEmptyString_whenInputEmptyMap() {
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        String expected = "";
        assertEquals(expected, formatter.formattingResult(map));

    }

    @Test
    void formattingResult_shouldReturnNullPointerException_whenInputNull() {
        assertThrows(NullPointerException.class, () -> formatter.formattingResult(null));

    }
}

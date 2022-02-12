package com.foxminded.counter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class CharCounterTest {
    CharCounter counter = new CharCounter();

    @Test
    void countCharacters_shouldReturnFilledMap_whenInputPhrase() {
        String phrase = "hello world";
        Map<Character, Integer> expected = new LinkedHashMap<Character, Integer>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);

        assertEquals(expected, counter.countCharacters(phrase));
    }

    @Test
    void countCharacters_shouldReturnFilledMap_whenInputOneSpace() {
        String phrase = " ";
        Map<Character, Integer> expected = new LinkedHashMap<Character, Integer>();
        expected.put(' ', 1);

        assertEquals(expected, counter.countCharacters(phrase));
    }

    @Test
    void countCharacters_shouldReturnNullPoinerException_whenInputNull() {
        String phrase = null;
        assertThrows(NullPointerException.class, () -> counter.countCharacters(phrase));
    }

    @Test
    void countCharacters_shouldReturnEmptyMap_whenInputEmptyString() {
        String phrase = "";
        Map<Character, Integer> expected = new LinkedHashMap<Character, Integer>();
        assertEquals(expected, counter.countCharacters(phrase));
    }

}

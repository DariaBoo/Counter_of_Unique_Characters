package com.foxminded.charCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.cache.Cache;
import com.foxminded.counter.Counter;

@ExtendWith(MockitoExtension.class)
class CharCalculatorTest {
    private Map<Character, Integer> map;
    private String phrase;
    private String expected;

    @Mock
    Cache mockedCache;

    @Mock
    Counter mockedCounter;

    @InjectMocks
    CharCalculator charCalculator;

    @BeforeEach
    void init() {
        map = new LinkedHashMap<Character, Integer>();
        map.put('h', 1);
        map.put('e', 1);
        map.put('l', 3);
        map.put('o', 2);
        map.put(' ', 1);
        map.put('w', 1);
        map.put('r', 1);
        map.put('d', 1);
        phrase = "hello world";
        expected = "\"h\" - 1\n" + "\"e\" - 1\n" + "\"l\" - 3\n" + "\"o\" - 2\n" + "\" \" - 1\n" + "\"w\" - 1\n"
                + "\"r\" - 1\n" + "\"d\" - 1\n";
    }

    @Test
    @DisplayName("Test cache (Mocked cache)") 
    void initCharCounting_shouldCallMethosGetCache_whenInpuAnExistingWordInTheCache() {
        Mockito.when(mockedCache.isContainsCache(phrase)).thenReturn(true); 
        charCalculator.initCharCounting(phrase); 
        Mockito.verify(mockedCache, never()).saveCache(phrase, new HashMap<Character, Integer>());
        Mockito.verify(mockedCache, atLeast(1)).getCache(phrase);
    }

    @Test
    @DisplayName("Test cache 2 (Real cache)")
    void initCharCounting_shouldNotCallCountCharactersMethod_whenInpuAnExistingWordInTheCache() {
        Cache cache = new Cache();
        cache.saveCache(phrase, map);
        charCalculator = new CharCalculator(cache, mockedCounter);
        
        assertEquals(expected, charCalculator.initCharCounting(phrase));
        Mockito.verify(mockedCounter, never()).countCharacters(phrase);     
    }

    @Test
    @DisplayName("Test counting method (Mocked CharCounter).")
    void initCharCounting_shouldCallCountCharactersMethod_whenInputANonExistentWordInTheCache() {
        Mockito.when(mockedCache.isContainsCache(phrase)).thenReturn(false);
        charCalculator.initCharCounting(phrase);
        Mockito.verify(mockedCache, never()).getCache(Mockito.any());
    }
    
    @Test
    @DisplayName("Test counting method 2 (Real CharCounter).")
    void initCharCounting_shouldReturnCountedString_whenInputANonExistentWordInTheCache2() {
        charCalculator = new CharCalculator(mockedCache, new Counter());
        
        Mockito.when(mockedCache.isContainsCache(phrase)).thenReturn(false);        
        assertEquals(expected, charCalculator.initCharCounting(phrase));      
        Mockito.verify(mockedCache, never()).getCache(Mockito.any());    
    }

   
    @Test
    @DisplayName("NullPointerException")
    void initCharCounting_shouldReturnNullPointerException_whenInputNull() {
        String phrase = null;
        assertThrows(NullPointerException.class, () -> charCalculator.initCharCounting(phrase));
    }
}

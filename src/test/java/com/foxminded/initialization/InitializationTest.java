package com.foxminded.initialization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.cache.Cache;
import com.foxminded.counter.CharCounter;

@ExtendWith(MockitoExtension.class)
class InitializationTest {   
    @Mock
    Cache mockedCache;
       
    @InjectMocks
    Initialization initialization;

    @Test
    @DisplayName("Input phrase was taken from cache.")
    void initCharCounting_shouldReturnStringTakenFromCache_whenInpuAnExistingWordInTheCache() {
        CharCounter mockedCounter = Mockito.mock(CharCounter.class);
        String phrase = "hello world";
        Map<Character, Integer> expectedMap = new LinkedHashMap<Character, Integer>();
        expectedMap.put('h', 1); 
        expectedMap.put('e', 1);
        expectedMap.put('l', 3);
        expectedMap.put('o', 2);
        expectedMap.put(' ', 1);
        expectedMap.put('w', 1);
        expectedMap.put('r', 1);
        expectedMap.put('d', 1);
        String expected = "\"h\" - 1\n" + "\"e\" - 1\n" + "\"l\" - 3\n" + "\"o\" - 2\n" + "\" \" - 1\n" + "\"w\" - 1\n"
                + "\"r\" - 1\n" + "\"d\" - 1\n";
      
        Mockito.when(mockedCache.isContainsCache(phrase)).thenReturn(true);
        Mockito.when(mockedCache.getCache(phrase)).thenReturn(expectedMap);
        Mockito.verify(mockedCounter, never()).countCharacters(phrase);
        Mockito.verify(mockedCache, never()).saveCache(expected, expectedMap);
        
        assertEquals(expected, initialization.initCharCounting(phrase));

    }
    
    @Test
    @DisplayName("Input phrase was calculated by method.")
    void initCharCounting_shouldReturnCountedString_whenInputANonExistentWordInTheCache() {
        String phrase = "hello world";
        String expected = "\"h\" - 1\n" + "\"e\" - 1\n" + "\"l\" - 3\n" + "\"o\" - 2\n" + "\" \" - 1\n" + "\"w\" - 1\n"
                + "\"r\" - 1\n" + "\"d\" - 1\n";

        Mockito.when(mockedCache.isContainsCache(phrase)).thenReturn(false);       
        Mockito.verify(mockedCache, never()).getCache(expected);
        assertEquals(expected, initialization.initCharCounting(phrase));
    }

    @Test
    @DisplayName("NullPointerException")
    void initCharCounting_shouldReturnNullPointerException_whenInputNull() {
        String phrase = null;
        assertThrows(NullPointerException.class, () -> initialization.initCharCounting(phrase));
    }
}

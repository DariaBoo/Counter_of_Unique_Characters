package com.foxminded.perfomanceTest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import com.foxminded.cache.Cache;
import com.foxminded.counter.Counter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PerformanceTestBenchmark {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @State(Scope.Thread)
    public static class PhraseExsitInTheCache {
        public Cache cache = new Cache();
        public String phrase = createPhrase();
        public Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();

        @Setup(Level.Trial)
        public void prepareCashe() {
            map.put('h', 10000000);
            map.put('e', 10000000);
            map.put('l', 30000000);
            map.put('o', 20000000);
            map.put(' ', 10000000);
            map.put('w', 10000000);
            map.put('r', 10000000);
            map.put('d', 10000000);
            cache.saveCache(phrase, map);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 1, warmups = 2)
    @Measurement(iterations = 3, time = 1)
    @Warmup(iterations = 5, time = 1)
    public void takeFromCache(PhraseExsitInTheCache test) {
        test.cache.getCache(test.phrase);
    }

    @State(Scope.Thread)
    public static class PhraseNotExistInTheCache {
        public Cache cache = new Cache();
        public Counter counter = new Counter();
        public String phrase = createPhrase();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Fork(value = 1, warmups = 2)
    @Measurement(iterations = 3, time = 1)
    @Warmup(iterations = 5, time = 1)
    public void countByMethod(PhraseNotExistInTheCache test, Blackhole blackhole) {
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        map = test.counter.countCharacters(test.phrase);
        test.cache.saveCache(test.phrase, map);
        blackhole.consume(map);
    }

    private static String createPhrase() {
        StringBuilder phrase = new StringBuilder();
        for (int i = 0; i < 10000000; i++) {
            phrase.append("hello world ");
        }
        return phrase.toString();
    }
}

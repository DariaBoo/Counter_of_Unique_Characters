package com.foxminded;

import com.foxminded.charCalculator.CharCalculator;
import com.foxminded.reader.InputReader;

public class Main {
    public static void main(String[] args) {
        InputReader console = new InputReader();
        CharCalculator initialization = new CharCalculator();
        System.out.print(initialization.initCharCounting(console.readString()));
    }
}

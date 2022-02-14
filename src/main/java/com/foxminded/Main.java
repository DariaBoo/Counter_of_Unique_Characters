package com.foxminded;

import com.foxminded.initialization.Initialization;
import com.foxminded.reader.InputReader;

public class Main {
    public static void main(String[] args) {
        InputReader console = new InputReader();
        Initialization initialization = new Initialization();
        System.out.print(initialization.initCharCounting(console.readString()));
    }
}

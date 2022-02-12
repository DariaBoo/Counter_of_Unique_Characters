package com.foxminded.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    public String readString() {
        String result = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter string to count unique characters:");
            result = bufferedReader.readLine();
        } catch (IOException exception) {          
           throw new RuntimeException("IOException in InputReader class.", exception);
        }
        return result;
    }
}

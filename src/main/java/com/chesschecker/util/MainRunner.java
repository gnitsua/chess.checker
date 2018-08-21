package com.chesschecker.util;
import com.chesschecker.input.InputParser;

import java.io.IOException;
import java.io.PrintStream;

public class MainRunner {
    public static void main(String[] args){
        InputParser ip = new InputParser(System.in,System.out);

        try {
            System.out.println(ip.parseInput());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(PrintStream out) {
        out.print("Hello World");
    }
}

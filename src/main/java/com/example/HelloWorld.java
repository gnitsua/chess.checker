package com.example;
import java.io.IOException;
import java.io.PrintStream;

public class HelloWorld {
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

package com.example;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InputParser {
    public static final String WHITE_PROMPT = "WHITE: ";
    public static final String BLACK_PROMPT = "BLACK: ";
    public static final String MOVE_PROMPT = "PIECE TO MOVE: ";
    private BufferedReader input;
    private PrintWriter output;

    InputParser(InputStream source, OutputStream prompt) {
        super();
        this.input = new BufferedReader(new InputStreamReader(source));
        this.output = new PrintWriter(new OutputStreamWriter(prompt));
    }

    private String readLine() throws IOException {
        return this.input.readLine();
    }

    private HashSet<String> promptForInput(String prompt) throws IOException {
        this.output.write(prompt);
        this.output.flush();
        final String s = this.readLine();
        final String[] pieces = s.split(", ");
        final List<String> strings = Arrays.asList(pieces);
        HashSet<String> pieces_set = new HashSet<>(strings);
        return pieces_set;
    }

    public BoardState parseInput() throws IOException {
        HashSet<String> white = this.promptForInput(WHITE_PROMPT);
        HashSet<String> black = this.promptForInput(BLACK_PROMPT);
        // making this assumption that this is a valid input and therefor |move|=1
        HashSet<String> move = this.promptForInput(MOVE_PROMPT);
        return new BoardState(white, black, move);
    }

}

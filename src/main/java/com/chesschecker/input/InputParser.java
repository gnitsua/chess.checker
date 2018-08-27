package com.chesschecker.input;

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

    public InputParser(InputStream source, OutputStream prompt) {
        super();
        this.input = new BufferedReader(new InputStreamReader(source));
        this.output = new PrintWriter(new OutputStreamWriter(prompt));
    }

    private String readLine() throws IOException {
        return this.input.readLine();
    }

    private PieceList promptForInput(String prompt) throws IOException {
        this.output.write(prompt);
        this.output.flush();
        final String s = this.readLine();
        return new PieceList(s);
    }

    public BoardState parseInput() throws IOException {
        final PieceList white = this.promptForInput(InputParser.WHITE_PROMPT);
        final PieceList black = this.promptForInput(InputParser.BLACK_PROMPT);
        // making this assumption that this is a valid input and therefor |move|=1
        final PieceList move = this.promptForInput(InputParser.MOVE_PROMPT);
        return BoardStateFactory.createBoardState(white,black,move);
    }

}

package com.chesschecker.input;

import com.chesschecker.gamestate.BoardState;
import com.chesschecker.gamestate.BoardStateFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("ClassNamePrefixedWithPackageName")
public
class InputParser {
    private static final String WHITE_PROMPT = "WHITE: ";
    private static final String BLACK_PROMPT = "BLACK: ";
    private static final String MOVE_PROMPT = "PIECE TO MOVE: ";
    private final BufferedReader input;
    private final PrintWriter output;

    public InputParser(final InputStream source, final OutputStream prompt) {
        super();
        this.input = new BufferedReader(new InputStreamReader(source, StandardCharsets.UTF_8));
        this.output = new PrintWriter(new OutputStreamWriter(prompt, StandardCharsets.UTF_8));
    }

    private String readLine() throws IOException {
        return this.input.readLine();
    }

    private PieceList promptForInput(final String prompt) throws IOException {
        this.output.write(prompt);
        this.output.flush();
        final String s = this.readLine();
        return new PieceList(s);
    }

    public final BoardState parseInput() throws IOException {
        final PieceList white = this.promptForInput(InputParser.WHITE_PROMPT);
        final PieceList black = this.promptForInput(InputParser.BLACK_PROMPT);
        // making this assumption that this is a valid input and therefor |move|=1
        final PieceList move = this.promptForInput(InputParser.MOVE_PROMPT);
        return BoardStateFactory.createBoardState(white, black, move);
    }

}

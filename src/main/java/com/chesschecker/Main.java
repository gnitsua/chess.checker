package com.chesschecker;

import com.chesschecker.gamestate.BoardState;
import com.chesschecker.input.Board;
import com.chesschecker.input.InputParser;
import com.chesschecker.input.MoveList;
import com.chesschecker.moves.BoardMove;
import com.chesschecker.util.BitBoard;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        InputParser test = new InputParser(System.in, System.out);
        BoardState state = test.parseInput();
        Set<BoardMove> moves = state.getValidMoves();
        String result = "LEGAL MOVES FOR " +
                state.getMove().stream().collect(Collectors.joining(",")) + ": " +
                moves.stream().map(BoardMove::toBoringString).collect(Collectors.joining(" "));
        System.out.println(result);

    }
}

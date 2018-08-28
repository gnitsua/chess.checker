package com.chesschecker;

import com.chesschecker.gamestate.BoardState;
import com.chesschecker.input.Board;
import com.chesschecker.input.InputParser;
import com.chesschecker.input.MoveList;
import com.chesschecker.moves.BoardMove;
import com.chesschecker.util.BitBoard;

import java.io.IOException;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        InputParser test = new InputParser(System.in, System.out);
        BoardState state = test.parseInput();
        Set<BoardMove> moves = state.getValidMoves();
        System.out.println(moves);
        System.out.println(moves.stream().map(x->x.getAttacking()).reduce(Board::and));

    }
}

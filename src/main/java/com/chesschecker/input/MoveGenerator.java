package com.chesschecker.input;

import com.chesschecker.moves.*;
import com.chesschecker.util.Column;

import java.util.HashSet;

public interface MoveGenerator {
    static HashSet<BoardMove> getSudoValidMoves(final String position) {
        try {
            assert 3 == position.length();
            final String type = position.substring(0, 1);//TODO: this is assuming that the input is valid
            final int column = Column.columnLetterToNumber(position.substring(1, 2));
            final int row = Integer.parseInt(position.substring(2, 3));

            final HashSet<BoardMove> result = new HashSet<>(64);
            for (int i = 0; 8 > i; i++) {
                for (int j = 0; 8 > j; j++) {
                    switch (type) {
                        case "K":
                            result.add(new KingMove(row, column, i, j));
                            break;
                        case "Q":
                            result.add(new QueenMove(row, column, i, j));
                            break;
                        case "R":
                            result.add(new RookMove(row, column, i, j));
                            break;
                        case "B":
                            result.add(new BishopMove(row, column, i, j));
                            break;
                        case "N":
                            result.add(new KnightMove(row, column, i, j));
                            break;
                        case "P":
                            result.add(new PawnMove(row, column, i, j));
                            result.add(new PawnCaptureMove(row, column, i, j));
                            result.add(new Pawn37DMove(row, column, i, j));
                            break;
                        default:
                            throw new AssertionError("Input was invalid");
                    }
                }
            }
            return result;
        } catch (AssertionError e) {
            return new HashSet<BoardMove>();
        }
    }
}

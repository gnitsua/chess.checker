package com.chesschecker.input;

import com.chesschecker.util.BitBoard;
import com.chesschecker.moves.*;
import com.chesschecker.util.Column;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveList extends HashSet<BoardMove> {
    public MoveList() {
        super();
    }

    MoveList(final HashSet<String> positions) {
        super();
        positions.stream().map(MoveList::getSudoValidMoves).flatMap(x -> x).map(this::add);
    }

//    static MoveList flipRows(final MoveList list) {
//        final HashSet<String> result = new HashSet<>(0);
//        list.forEach(piece -> {
//            final char row = piece.charAt(2);
//            final char newRow = (char) (((int) '8' - (int) row) + (int) '1');
//            result.add(piece.substring(0, 2) + newRow);
//        });
//        return result;
//
//    }


    static Stream<BoardMove> getSudoValidMoves(final String position) {
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
                            //Don't add moves that don't have a valid piece type
                            break;
                    }
                }
            }
            return result.stream();
        } catch (final AssertionError e) {
            return new HashSet<BoardMove>(0).stream();
        }
    }

    public BitBoard getOccupancy() {
        return this.stream().map(BoardMove::getOccupancy).reduce(new Board(),Board::and);
    }

    public BitBoard getAttacking() {
        return this.stream().map(BoardMove::getAttacking).reduce(new Board(),Board::and);
    }
}

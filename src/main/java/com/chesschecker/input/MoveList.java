package com.chesschecker.input;

import com.chesschecker.moves.*;
import com.chesschecker.util.BitBoard;
import com.chesschecker.util.Column;
import com.chesschecker.util.PieceAbbreviation;
import com.google.common.base.Predicates;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MoveList extends HashSet<BoardMove> {

    MoveList() {
        super();
    }

    public MoveList(final Collection<String> positions) {
        super();
        //noinspection ChainedMethodCall,ResultOfMethodCallIgnored
        positions.stream().map(MoveList::getPseudoLegalMovesForPosition)
                .map(Collection::stream).flatMap(x -> x).map(this::add);
    }

    static Collection<BoardMove> getPseudoLegalMovesForPosition(final String position) {
        final Collection<BoardMove> result = new HashSet<>(64);
        try {
            assert 3 == position.length();
            final String type = position.substring(0, 1);//TODO: this is assuming that the input is valid
            final int column = Column.columnLetterToNumber(position.substring(1, 2));
            final int row = Integer.parseInt(position.substring(2, 3));
            for (int i = 0; 8 > i; i++) {
                for (int j = 0; 8 > j; j++) {
                    switch (PieceAbbreviation.valueOf(type)) {
                        case KING:
                            result.add(new KingMove(row, column, i, j));
                            break;
                        case QUEEN:
                            result.add(new QueenMove(row, column, i, j));
                            break;
                        case ROOK:
                            result.add(new RookMove(row, column, i, j));
                            break;
                        case BISHOP:
                            result.add(new BishopMove(row, column, i, j));
                            break;
                        case KNIGHT:
                            result.add(new KnightMove(row, column, i, j));
                            break;
                        case PAWN:
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
            return result;
        } catch (final AssertionError e) {
            return result;
        }
    }



    public BitBoard getOccupancy() {
        return this.stream().map(BoardMove::getOccupancy).reduce(new Board(), Board::and);
    }

//    public BitBoard getAttacking() {
//        return this.stream().map(BoardMove::getAttacking).reduce(new Board(),Board::and);
//    }
}

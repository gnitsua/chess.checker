package com.chesschecker.input;

import com.chesschecker.moves.*;
import com.chesschecker.util.Column;
import com.chesschecker.util.PieceAbbreviation;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

@SuppressWarnings({"serial", "CloneableClassWithoutClone",
        "CloneableClassInSecureContext", "ClassExtendsConcreteCollection"})
class MoveList extends HashSet<BoardMove> {

    MoveList(final Collection<String> positions) {
        super();
        //noinspection ChainedMethodCall,ResultOfMethodCallIgnored
        MoveList.getPseudoLegalMovesForPositions(positions).map(this::add);
    }

    private static Stream<BoardMove> getPseudoLegalMovesForPositions(final Collection<String> positions) {
        //noinspection ChainedMethodCall,ResultOfMethodCallIgnored
        return positions.stream().map(MoveList::getPseudoLegalMovesForPosition)
                .map(Collection::stream).flatMap(x -> x);
    }


    @SuppressWarnings({"ObjectAllocationInLoop", "SwitchStatement", "OverlyComplexMethod", "OverlyLongMethod"})
    static Collection<BoardMove> getPseudoLegalMovesForPosition(final String position) {
        final Collection<BoardMove> result = new HashSet<>(64);
        //noinspection ErrorNotRethrown
        try {
            assert 3 == position.length();
            final String type = position.substring(0, 1);//this is assuming that the input is valid
            final int column = Column.columnLetterToNumber(position.substring(1, 2));
            final int row = Integer.parseInt(position.substring(2, 3));
            for (int i = 0; 8 > i; i++) {
                for (int j = 0; 8 > j; j++) {
                    //noinspection NestedTryStatement
                    try {
                        switch (PieceAbbreviation.fromAbbreviation(type)) {
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
                    } catch (final IllegalArgumentException e) {
                        //do nothing
                    }
                }
            }
            return result;
        } catch (final AssertionError e) {
            return result;
        }
    }


//
//    public BitBoard getOccupancy() {
//        return this.stream().map(BoardMove::getOccupancy).reduce(new Board(), Board::and);
//    }

//    public BitBoard getAttacking() {
//        return this.stream().map(BoardMove::getAttacking).reduce(new Board(),Board::and);
//    }
}

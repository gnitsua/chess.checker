//package com.chesschecker.input;
//
//import com.chesschecker.util.BitBoard;
//import com.chesschecker.moves.*;
//import com.chesschecker.util.Column;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
//public class MoveList extends HashSet<BoardMove> {
//    public MoveList() {
//        super();
//    }
//
//    MoveList(final String[] whiteline) {
//        super();
//        for (String s : Arrays.asList(whiteline)) {
//            HashSet<BoardMove> sudoValidMoves = getSudoValidMoves(s);
//            this.addAll(sudoValidMoves);
//        }
//    }
//
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
//
//    static HashSet<BoardMove> getSudoValidMoves(final String position) {
//        try {
//            assert 3 == position.length();
//            final String type = position.substring(0, 1);//TODO: this is assuming that the input is valid
//            final int column = Column.columnLetterToNumber(position.substring(1, 2));
//            final int row = Integer.parseInt(position.substring(2, 3));
//
//            final HashSet<BoardMove> result = new HashSet<>(64);
//            for (int i = 0; 8 > i; i++) {
//                for (int j = 0; 8 > j; j++) {
//                    switch (type) {
//                        case "K":
//                            result.add(new KingMove(row, column, i, j));
//                            break;
//                        case "Q":
//                            result.add(new QueenMove(row, column, i, j));
//                            break;
//                        case "R":
//                            result.add(new RookMove(row, column, i, j));
//                            break;
//                        case "B":
//                            result.add(new BishopMove(row, column, i, j));
//                            break;
//                        case "N":
//                            result.add(new KnightMove(row, column, i, j));
//                            break;
//                        case "P":
//                            result.add(new PawnMove(row, column, i, j));
//                            result.add(new PawnCaptureMove(row, column, i, j));
//                            result.add(new Pawn37DMove(row, column, i, j));
//                            break;
//                        default:
//                            throw new AssertionError("Input was invalid");
//                    }
//                }
//            }
//            return result;
//        } catch (AssertionError e) {
//            return new HashSet<BoardMove>(0);
//        }
//    }
//
//    public BitBoard getOccupancy() {
//        BitBoard result = new BitBoard();
//        this.forEach(boardMove -> result.setOccupancy(boardMove.getStartRow(), boardMove.getStartCol()));
//        return result;
//    }
//
//    public BitBoard getAttacking() {
//        BitBoard result = new BitBoard();
//        this.forEach(boardMove -> result.setOccupancy(boardMove.getEndRow(), boardMove.getEndRow()));
//        return result;
//    }
//}

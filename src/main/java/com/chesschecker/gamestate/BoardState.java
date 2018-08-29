package com.chesschecker.gamestate;

import com.chesschecker.input.MoveList;
import com.chesschecker.input.PieceList;
import com.chesschecker.moves.BoardMove;
import com.chesschecker.moves.Move;
import com.chesschecker.util.BitBoard;
import com.chesschecker.util.StringHelper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BoardState {

    @SuppressWarnings("FieldNotUsedInToString")
    Set<String> white;
    @SuppressWarnings("FieldNotUsedInToString")
    Set<String> black;

    Set<String> move;

    BoardState() {
        super();
        this.white = new HashSet<>(0);
        this.black = new HashSet<>(0);
        this.move = new HashSet<>(0);
    }

    BoardState(final Set<String> whitein, final Set<String> blackin) {
        super();
        this.white = whitein;
        this.black = blackin;
        this.move = new HashSet<>(0);
    }

    BoardState(final Set<String> whitein, final Set<String> blackin, final Set<String> movein) {
        super();
        this.white = whitein;
        this.black = blackin;
        this.move = movein;
    }

//    static BoardState getNextState(final BoardState currentState, BoardMove move) {
//        // We make the assumption that there is no way for two pieces to share the same location
//        // therefor start position as a string should only match one position
//        PieceList nextWhite = new PieceList(PieceList.filterMoves(currentState.getOrientedWhite(), move.startPositionToString() + "$"));
//        nextWhite.add(move.toString());
//        PieceList nextBlack = new PieceList(PieceList.filterMoves(currentState.getOrientedBlack(), move.startPositionToString() + "$"));
//        return BoardStateFactory.createBoardState(nextWhite, nextBlack, move);
//    }

    public final Set<String> getWhiteWithoutKing() {
        //noinspection ChainedMethodCall
        return PieceList.filterOutKing(this.getOrientedWhite());
    }

    public final Set<String> getWhiteKing() {
        final Set<String> allWhite = new HashSet<>(this.getOrientedWhite());
        final Set<String> whiteWithoutKing = this.getWhiteWithoutKing();
        allWhite.removeAll(whiteWithoutKing);//intersection is just white king
        return allWhite;
    }

    public final Set<String> getBlackWithoutKing() {
        return PieceList.filterOutKing(this.getOrientedBlack());
    }

    public final Set<String> getBlackKing() {
        final Set<String> allBlack = new HashSet<>(this.getOrientedBlack());
        final Set<String> blackWithoutKing = this.getBlackWithoutKing();
        allBlack.removeAll(blackWithoutKing);//intersection is just white king
        return allBlack;
    }

    public final Set<String> getWhite() {
        return this.white;
    }

    public final Set<String> getBlack() {
        return this.black;
    }

    public final Set<BoardMove> getValidMoves() {
        MoveList pseudoWhiteMoves = new MoveList(this.getWhite());//without king enables Xray attacks
        BitBoard whiteOccupancy = pseudoWhiteMoves.getOccupancy();//TODO: this is a super inefficient way to get this
//        System.out.println(whiteOccupancy);
//        return null;
//        //Move list expects positions to be from the white perspective, so flip first
        MoveList pseudoBlackAttacks = new MoveList(PieceList.flipRows(this.getBlack()));
        BitBoard blackOccupancy = pseudoBlackAttacks.getOccupancy();
        blackOccupancy.mirrorVertical();//TODO: does this mean there should be a BlackMoveList class?
//
//        Set<BoardMove> blackAttacks = pseudoBlackAttacks.stream()
//                .filter(x -> x.isValid(blackOccupancy, whiteOccupancy))
//                .collect(Collectors.toSet());
//
//        MoveList psuedoMovesForCheck = new KingProtectionMoveList(this.getWhiteKing());
//        //Moves that could put white into check are any black moves into the King position
//        // Because moves are symmetric, this also means that this is the same as the reverse
//        // of white moves from king.
//        Set<BoardMove> movesForCheck = psuedoMovesForCheck.stream()
//                .filter(x -> x.isValid(whiteOccupancy, blackOccupancy))
//                .map(BoardMove::reverse).collect(Collectors.toSet());
//TODO: need to check both with the piece removed, and with it in its new location
//
        MoveList psuedoMovesForMove = new MoveList(this.move);
        Set<BoardMove> movesForMoveWithoutPins = psuedoMovesForMove.stream()
                .filter(x -> x.isValid(whiteOccupancy, blackOccupancy)).collect(Collectors.toSet());
////        Set<BoardMove> movesForMoveWithPins = movesForMoveWithoutPins.filter();
//        return null;

//        return.filter
        return movesForMoveWithoutPins;
    }

    abstract Set<String> getOrientedWhite();

    abstract Set<String> getOrientedBlack();

    @Override
    public final String toString() {
        return String.valueOf(this.getWhite()) +
                StringHelper.NEW_LINE +
                this.getBlack() +
                StringHelper.NEW_LINE;
    }
}

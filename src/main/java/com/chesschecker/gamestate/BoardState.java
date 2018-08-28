package com.chesschecker.gamestate;

import com.chesschecker.input.PieceList;

import java.util.HashSet;
import java.util.Set;

public abstract class BoardState {

    @SuppressWarnings("FieldNotUsedInToString")
    Set<String> white;
    @SuppressWarnings("FieldNotUsedInToString")
    Set<String> black;

    BoardState() {
        super();
        this.white = new HashSet<>(0);
        this.black = new HashSet<>(0);
    }

    BoardState(final Set<String> whitein, final Set<String> blackin) {
        super();
        this.white = whitein;
        this.black = blackin;
    }

    public final Set<String> getWhiteWithoutKing() {
        //noinspection ChainedMethodCall
        return PieceList.filterOutKing(this.getWhite());
    }

    public final Set<String> getWhiteKing() {
        final Set<String> allWhite = new HashSet<>(this.getWhite());
        final Set<String> whiteWithoutKing = this.getWhiteWithoutKing();
        allWhite.retainAll(whiteWithoutKing);//intersection is just white king
        return allWhite;
    }

    public final Set<String> getBlackWithoutKing() {
        return PieceList.filterOutKing(this.getBlack());
    }

    public final Set<String> getBlackKing() {
        final Set<String> allBlack = new HashSet<>(this.getBlack());
        final Set<String> blackWithoutKing = this.getBlackWithoutKing();
        allBlack.retainAll(blackWithoutKing);//intersection is just white king
        return allBlack;
    }

    public final Set<String> getWhite() {
        return this.white;
    }


    public final Set<String> getBlack() {
        return this.black;
    }

//    static BoardState getNextState(final BoardState currentState, Move move) {
//
//    }

//    public final MoveList getValidMoves() {
//        MoveList pseudoWhiteMoves = new MoveList(this.getWhiteWithoutKing());//without king enables Xray attacks
//        BitBoard whiteOccupancy = pseudoWhiteMoves.getOccupancy();//TODO: this is a super inefficient way to get this
//
//        //Move list expects positions to be from the white perspective, so flip first
//        MoveList pseudoBlackAttacks = new MoveList(WhiteBoardState.flip_rows(this.getBlack()));
//        BitBoard blackOccupancy = pseudoBlackAttacks.getOccupancy();
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
//
//
//        MoveList psuedoMovesForMove = new MoveList(this.getMove());
//        Stream<BoardMove> movesForMoveWithoutPins = psuedoMovesForMove.stream()
//                .filter(x -> x.isValid(whiteOccupancy, blackOccupancy));
////        Set<BoardMove> movesForMoveWithPins = movesForMoveWithoutPins.filter();
//        return null;
//
////        return.filter
//    }

//    abstract Set<String> getOrientedWhite();

//    abstract Set<String> getOrientedBlack();

    public abstract String toString();
}

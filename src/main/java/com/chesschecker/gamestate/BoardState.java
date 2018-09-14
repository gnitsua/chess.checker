package com.chesschecker.gamestate;

import com.chesschecker.input.Board;
import com.chesschecker.input.MoveList;
import com.chesschecker.input.PieceList;
import com.chesschecker.moves.BoardMove;
import com.chesschecker.moves.Move;
import com.chesschecker.util.BitBoard;
import com.chesschecker.util.StringHelper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public final Set<String> getMove() {
        return this.move;
    }

    /**
     * @return This Bitboard could be white or black occupancy depending on the current move
     */
    BitBoard getFriendlyOccupancy() {
        MoveList pseudoFriendlyMoves = new MoveList(this.getOrientedWhite());//without king enables Xray attacks
        return pseudoFriendlyMoves.getOccupancy();//TODO: this is a super inefficient way to get this
    }

    BitBoard getFriendlyKingOccupancy() {
        MoveList noKing = new MoveList(this.getWhiteKing());//No king allows for check of xray attack
        return noKing.getOccupancy();
    }

    BitBoard getFoeOccupancy() {
        MoveList pseudoFoeMoves = new MoveList(this.getOrientedBlack());
        return pseudoFoeMoves.getOccupancy();
    }

    protected Set<BoardMove> getValidWhiteMoves() {
        BitBoard friendly = this.getFriendlyOccupancy();
        BitBoard foe = this.getFoeOccupancy();
        BitBoard friendlyKing = this.getFriendlyKingOccupancy();
        BitBoard friendlyNoKing = Board.xor(friendly,friendlyKing);



//        //Move list expects positions to be from the white perspective, so flip first
        MoveList pseudoBlackAttacks = new MoveList(PieceList.flipRows(this.getOrientedBlack()));
        Set<BoardMove> blackAttacksReversed = pseudoBlackAttacks.stream()
                .filter(x -> x.isValid(Board.mirrorVertical(friendlyNoKing), Board.mirrorVertical(foe)))
                .collect(Collectors.toSet());
        Set<BoardMove> blackAttacks = MoveList.getEvilTwinList(blackAttacksReversed);
        System.out.println(blackAttacks);
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
        MoveList pseudoFriendlyMoves = new MoveList(this.getOrientedMove());//without king enables Xray attacks

        Set<BoardMove> movesForMoveWithoutPins = pseudoFriendlyMoves.stream()
                .filter(x -> x.isValid(friendly, foe)).collect(Collectors.toSet());
////        Set<BoardMove> movesForMoveWithPins = movesForMoveWithoutPins.filter();
//        return null;

//        return.filter
        return movesForMoveWithoutPins;
    }

    public abstract Set<BoardMove> getValidMoves();

    abstract Set<String> getOrientedWhite();

    abstract Set<String> getOrientedBlack();

    abstract Set<String> getOrientedMove();

    @Override
    public final String toString() {
        return String.valueOf(this.getWhite()) +
                StringHelper.NEW_LINE +
                this.getBlack() +
                StringHelper.NEW_LINE;
    }
}

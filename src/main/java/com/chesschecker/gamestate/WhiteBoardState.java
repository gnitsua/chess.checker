package com.chesschecker.gamestate;

import com.chesschecker.moves.BoardMove;

import java.util.Set;


/**
 * Class representing the board state such that the piece being moved is always white
 */
@SuppressWarnings("PublicMethodNotExposedInInterface")
public class WhiteBoardState extends BoardState {

    public WhiteBoardState() {
        super();
    }

    public WhiteBoardState(final Set<String> whiteIn, final Set<String> blackIn, final Set<String> moveIn) {
        super(whiteIn, blackIn, moveIn);
    }

    public WhiteBoardState(final Set<String> whiteIn, final Set<String> blackIn) {
        super(whiteIn, blackIn);
    }

    @SuppressWarnings("SuspiciousGetterSetter")
    public final Set<String> getOrientedWhite() {
        return this.white;
    }


    @SuppressWarnings("SuspiciousGetterSetter")
    public final Set<String> getOrientedBlack() {
        return this.black;
    }

    public final Set<BoardMove> getValidMoves() {
        return super.getValidWhiteMoves();
    }
}

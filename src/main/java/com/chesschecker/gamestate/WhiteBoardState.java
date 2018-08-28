package com.chesschecker.gamestate;

import com.chesschecker.input.PieceList;
import com.chesschecker.util.StringHelper;

import java.util.Set;


/**
 * Class representing the board state such that the piece being moved is always white
 */
@SuppressWarnings("PublicMethodNotExposedInInterface")
public class WhiteBoardState extends BoardState {

    public WhiteBoardState() {
        super();
    }

    public WhiteBoardState(final PieceList whiteIn, final PieceList blackIn) {
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


    @Override
    public final String toString() {
        return String.valueOf(this.getWhite()) +
                StringHelper.NEW_LINE +
                this.getBlack() +
                StringHelper.NEW_LINE;
    }
}

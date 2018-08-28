package com.chesschecker.gamestate;

import com.chesschecker.input.PieceList;
import com.chesschecker.util.StringHelper;

import java.util.Set;

public class BlackBoardState extends BoardState {
    public BlackBoardState(final PieceList whiteIn, final PieceList blackIn) {
        super(whiteIn, blackIn);
    }

    public final Set<String> getOrientedWhite() {
        return PieceList.flipRows(this.black);
    }


    public final Set<String> getOrientedBlack() {
        return PieceList.flipRows(this.white);
    }

    @Override
    public final String toString() {
        return String.valueOf(this.getWhite()) +
                StringHelper.NEW_LINE +
                this.getBlack() +
                StringHelper.NEW_LINE;
    }
}

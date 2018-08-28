package com.chesschecker.gamestate;

import com.chesschecker.input.PieceList;

import java.util.Set;

public class BlackBoardState extends BoardState {
    public BlackBoardState(final PieceList whiteIn, final PieceList blackIn, final PieceList moveIn) {
        super(whiteIn, blackIn, moveIn);
    }

    public BlackBoardState(final PieceList whiteIn, final PieceList blackIn) {
        super(whiteIn, blackIn);
    }

    public final Set<String> getOrientedWhite() {
        return PieceList.flipRows(this.black);
    }


    public final Set<String> getOrientedBlack() {
        return PieceList.flipRows(this.white);
    }


}

package com.chesschecker.gamestate;

import com.chesschecker.input.PieceList;

public final class BoardStateFactory {

    public static BoardState createBoardState(final PieceList whiteIn, final PieceList blackIn, final PieceList moveIn) {
        assert 1 == moveIn.size();
        if (whiteIn.containsAll(moveIn)) {
            return new WhiteBoardState(whiteIn,blackIn,moveIn);
        } else {
            if (blackIn.containsAll(moveIn)) {
                return new BlackBoardState(whiteIn,blackIn,moveIn);
            } else {// The piece that is supposed to be moved is not actually a piece. Or
                    // the piece list contains pieces that are both black and white
                    // Default to white, and return no pieces
                return new WhiteBoardState();
            }
        }
    }

}

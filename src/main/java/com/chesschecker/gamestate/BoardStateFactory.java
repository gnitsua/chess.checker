package com.chesschecker.gamestate;

import com.chesschecker.input.PieceList;

public final class BoardStateFactory {
    private BoardStateFactory() {
        super();
    }

    public static BoardState createBoardState(final PieceList whiteIn, final PieceList blackIn, final PieceList moveIn) {
        if (whiteIn.containsAll(moveIn)) {
            return new WhiteBoardState(whiteIn,blackIn,moveIn);
        } else {
            if (blackIn.containsAll(moveIn)) {
                return new BlackBoardState(whiteIn,blackIn,moveIn);
            } else {// The piece that is supposed to be moved is not actually a piece.
                    // Default to white, and return no pieces
                return new WhiteBoardState();
            }
        }
    }

}

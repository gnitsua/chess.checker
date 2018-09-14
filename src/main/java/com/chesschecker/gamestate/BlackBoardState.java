package com.chesschecker.gamestate;

import com.chesschecker.input.MoveList;
import com.chesschecker.input.PieceList;
import com.chesschecker.moves.BoardMove;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @SuppressWarnings("SuspiciousGetterSetter")
    public final Set<String> getOrientedMove() { return PieceList.flipRows(this.move); }

    public final Set<BoardMove> getValidMoves() {
        return MoveList.getEvilTwinList(super.getValidWhiteMoves());
    }


}

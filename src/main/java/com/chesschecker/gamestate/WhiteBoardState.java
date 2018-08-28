package com.chesschecker.gamestate;

import java.util.Set;


/**
 * Class representing the board state such that the piece being moved is always white
 */
@SuppressWarnings("PublicMethodNotExposedInInterface")
public class WhiteBoardState extends BoardState {

    public WhiteBoardState() {
        super();
    }

    WhiteBoardState(final Set<String> whiteIn, final Set<String> blackIn, final Set<String> moveIn) {
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
}

package com.chesschecker.input;

import com.chesschecker.gamestate.BlackBoardState;
import com.chesschecker.gamestate.BoardState;
import com.chesschecker.gamestate.BoardStateFactory;
import com.chesschecker.gamestate.WhiteBoardState;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.instanceOf;

@SuppressWarnings("ALL")
public class BoardStateFactoryTest {

    @Test
    public void test_ForWhiteMoves() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList move = new PieceList("Rf1");
        BoardState sut = BoardStateFactory.createBoardState(white, black,move);

        Assert.assertThat(sut, instanceOf(WhiteBoardState.class));
    }

    @Test
    public void test_ForBlackMoves() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList move = new PieceList("Pa7");
        BoardState sut = BoardStateFactory.createBoardState(white, black,move);

        Assert.assertThat(sut, instanceOf(BlackBoardState.class));
    }

    @Test
    public void test_ForNoValidMoves() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList move = new PieceList("Pf1");
        HashSet<String> empty = new HashSet<>();
        BoardState sut = BoardStateFactory.createBoardState(white, black,move);

        Assert.assertThat(sut, instanceOf(WhiteBoardState.class));
    }
}
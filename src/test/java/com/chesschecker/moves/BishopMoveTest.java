package com.chesschecker.moves;

import com.chesschecker.input.Board;
import com.chesschecker.util.BitBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.notNull;

@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class BishopMoveTest {
    @Parameterized.Parameter(value = 0)
    public int startrow;
    @Parameterized.Parameter(value = 1)
    public int startcol;
    @Parameterized.Parameter(value = 2)
    public int endrow;
    @Parameterized.Parameter(value = 3)
    public int endcol;
    @Parameterized.Parameter(value = 4)
    public Board friendly;
    @Parameterized.Parameter(value = 5)
    public Board foe;
    @Parameterized.Parameter(value = 6)
    public String expectedString;
    @Parameterized.Parameter(value = 7)
    public boolean expected;


    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        BitBoard empty = new Board();

        return Arrays.asList(new Object[][]{
                /*
                * Tests for Bishop Move
                */
                {3, 4, 6, 7, empty, empty, "Bh7", true},
                {3, 4, 0, 7, empty, empty, "Bh1", true},
                {3, 4, 7, 0, empty, empty, "Ba8", true},
                {3, 4, 0, 1, empty, empty, "Bb1", true},

                /*
                * Tests for not Rook Move
                */
                {2, 3, 2, 7, empty, empty, "Bh3", false},
                {2, 3, 2, 0, empty, empty, "Ba3", false},
                {2, 3, 7, 3, empty, empty, "Bd8", false},
                {2, 3, 0, 3, empty, empty, "Bd1", false},

        });
    }

    @Test
    public void test_Move() {
        Board empty = new Board();
        BishopMove sut = new BishopMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        BishopMove sut = Mockito.spy(new BishopMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidQueenMove()).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidSlideMove(this.friendly, this.foe)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidColoredMove(this.friendly)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);//TODO: programatically figure out which methods to mock
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            BishopMove sut = new BishopMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

}
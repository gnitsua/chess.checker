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
public class KnightMoveTest {
    @Parameterized.Parameter(value = 0)
    public int startrow;
    @Parameterized.Parameter(value = 1)
    public int startcol;
    @Parameterized.Parameter(value = 2)
    public int endrow;
    @Parameterized.Parameter(value = 3)
    public int endcol;
    @Parameterized.Parameter(value = 4)
    public BitBoard friendly;
    @Parameterized.Parameter(value = 5)
    public BitBoard foe;
    @Parameterized.Parameter(value = 6)
    public String expectedString;
    @Parameterized.Parameter(value = 7)
    public boolean expected;


    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        BitBoard empty = new Board();
        BitBoard friendly = new Board();
        friendly.setOccupancy(0, 0);
        friendly.setOccupancy(0, 1);

        return Arrays.asList(new Object[][]{
                /**
                 * Tests for Knight
                */
                {2, 2, 1, 0, empty, empty, "Na2", true},
                {2, 2, 0, 1, empty, empty, "Nb1", true},
                {2, 2, 3, 0, empty, empty, "Na4", true},
                {2, 2, 4, 1, empty, empty, "Nb5", true},
                {2, 2, 4, 3, empty, empty, "Nd5", true},
                {2, 2, 3, 4, empty, empty, "Ne4", true},
                {2, 2, 1, 4, empty, empty, "Ne2", true},
                {2, 2, 0, 3, empty, empty, "Nd1", true},
                /**
                 * Moving too far
                */
                {2, 2, 0, 5, empty, empty, "Nf1", false},
                {2, 2, 5, 0, empty, empty, "Na6", false},

                /**
                 * Tests not Queen move
                */
                {3, 4, 5, 6, empty, empty, null, false},
                {3, 4, 5, 2, empty, empty, null, false},
                {3, 4, 3, 2, empty, empty, null, false},
                {3, 4, 1, 2, empty, empty, null, false},
                {2, 3, 2, 4, empty, empty, null, false},
        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        KnightMove sut = new KnightMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        KnightMove sut = Mockito.spy(new KnightMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidColoredMove(this.friendly)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);//TODO: programatically figure out which methods to mock
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            KnightMove sut = new KnightMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

}
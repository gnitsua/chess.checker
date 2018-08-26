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
public class Pawn37DMoveTest {
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

    private Class<Pawn37DMove> classUnderTest = Pawn37DMove.class; //class under test

    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        Board empty = new Board();
        Board friendly = new Board();
        friendly.setOccupancy(0, 0);//There will always be a friendly at the start position
        friendly.setOccupancy(4, 2);
        friendly.setOccupancy(4, 4);

        return Arrays.asList(new Object[][]{
                /**
                 * PawnCaptureMoveTests
                */
                {1, 3, 4, 2, empty, friendly, "Pc5", true},//left capture iff there is an enemy
                {1, 3, 4, 2, empty, empty, "Pc5", false},
                {1, 3, 4, 2, friendly, empty, "Pc5", false},
                {1, 3, 4, 4, empty, friendly, "Pe5", true},//right capture iff there is an enemy
                {1, 3, 4, 4, empty, empty, "Pe5", false},
                {1, 3, 4, 4, friendly, empty, "Pe5", false},

                {0, 0, 1, 2, empty, empty, "Pc2", false},

        });
    }

    @Test
    public void test_Move() {
        Board empty = new Board();
        Pawn37DMove sut = new Pawn37DMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        Pawn37DMove sut = Mockito.spy(new Pawn37DMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidColoredMove(this.friendly)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);//TODO: programatically figure out which methods to mock
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            Pawn37DMove sut = new Pawn37DMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

}
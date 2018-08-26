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

@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class ColoredMoveTest {
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
        friendly.setOccupancy(3, 3);

        return Arrays.asList(new Object[][]{
                /**
                 * This test ensures that a pieces is not allowed to land on it's friend and is allowed to
                 * land on an enemy
                */
                {0, 0, 3, 3, friendly, empty, "d4", false},
                {0, 0, 3, 3, empty, friendly, "d4", true},
                {0, 0, 0, 0, empty, friendly, "a1", false},
        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        ColoredMove sut = new ColoredMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        BitBoard empty = new Board();
        BoardMove sut = Mockito.spy(new ColoredMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);//TODO: programatically figure out which methods to mock
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            ColoredMove sut = new ColoredMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }
}
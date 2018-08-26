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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;

@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class SlideMoveTest {
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
        friendly.setOccupancy(3, 3);

        return Arrays.asList(new Object[][]{
                /**
                 * Tests for SlideMove
                */
                {3, 0, 3, 7, empty, empty, "h4", true},//Horizontal
                {3, 0, 3, 7, friendly, empty, "h4", false},//Horizontal blocked by friendly
                {3, 0, 3, 7, empty, friendly, "h4", false},//Horizontal blocked by foe
                {0, 3, 7, 3, empty, empty, "d8", true},//Vertical
                {0, 3, 7, 3, friendly, empty, "d8", false},//Vertical blocked by friendly
                {0, 3, 7, 3, empty, friendly, "d8", false},//Vertical blocked by foe
                {0, 0, 7, 7, empty, empty, "h8", true},//Diagonal1
                {0, 0, 7, 7, friendly, empty, "h8", false},//Diagonal1 blocked by friendly
                {0, 0, 7, 7, empty, friendly, "h8", false},//Diagonal1 blocked by foe
                {6, 0, 0, 6, empty, empty, "g1", true},//Diagonal2
                {6, 0, 0, 6, friendly, empty, "g1", false},//Diagonal2 blocked by friendly
                {6, 0, 0, 6, empty, friendly, "g1", false},//Diagonal2 blocked by foe

                {3, 0, 3, 3, friendly, empty, "d4", false},//Friendly on terminal position
                {3, 0, 3, 3, empty, friendly, "d4", true},//Foe on terminal position
        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        SlideMove sut = new SlideMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        SlideMove sut = Mockito.spy(new SlideMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidColoredMove(this.friendly)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            SlideMove sut = new SlideMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

}
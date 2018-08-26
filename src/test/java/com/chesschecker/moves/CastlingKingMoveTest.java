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
public class CastlingKingMoveTest {
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
        friendly.setOccupancy(0,3);
        friendly.setOccupancy(0,5);

        return Arrays.asList(new Object[][]{
                /**
                 * Tests for Castling king
                */
                {0, 4, 0, 2, empty, empty, "Kc1", true},//Left
                {0, 4, 0, 2, friendly, empty, "Kc1", false},//Left blocked by friendly
                {0, 4, 0, 2, empty, friendly, "Kc1", false},//Left blocked by foe
                {0, 4, 0, 6, empty, empty, "Kg1", true},//Right
                {0, 4, 0, 6, friendly, empty, "Kg1", false},//Right blocked by friendly
                {0, 4, 0, 6, empty, friendly, "Kg1", false},//Right blocked by foe

                {0, 3, 0, 2, empty, empty, "Kc1", false},//King out of position
                {0, 5, 0, 2, empty, empty, "Kc1", false},//King out of position 2

                {0, 4, 3, 7, empty, empty, "Kh4", false},//Horizontal
                {0, 4, 7, 3, empty, empty, "Kd8", false},//Vertical
                {0, 4, 0, 7, empty, empty, "Kh1", false},
                {1, 4, 1, 7, empty, empty, "Kh2", false},

                //TODO: does not check for check on the way there

        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        CastlingKingMove sut = new CastlingKingMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        CastlingKingMove sut = Mockito.spy(new CastlingKingMove(this.startrow, this.startcol, this.endrow, this.endcol));
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
            CastlingKingMove sut = new CastlingKingMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }
}
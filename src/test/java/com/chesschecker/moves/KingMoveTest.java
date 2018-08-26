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
public class KingMoveTest {
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

        return Arrays.asList(new Object[][]{
                {2, 2, 3, 3, empty, empty, "Kd4", true},
                {2, 2, 2, 3, empty, empty, "Kd3", true},
                {2, 2, 1, 3, empty, empty, "Kd2", true},
                {2, 2, 3, 2, empty, empty, "Kc4", true},
                {2, 2, 1, 2, empty, empty, "Kc2", true},
                {2, 2, 1, 1, empty, empty, "Kb2", true},
                {2, 2, 2, 1, empty, empty, "Kb3", true},
                {2, 2, 3, 1, empty, empty, "Kb4", true},

                {2, 2, 4, 2, empty, empty, "Kc5", false},
                {2, 2, 2, 4, empty, empty, "Ke3", false},

        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        KingMove sut = new KingMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        KingMove sut = Mockito.spy(new KingMove(this.startrow, this.startcol, this.endrow, this.endcol));
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
            KingMove sut = new KingMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }
}
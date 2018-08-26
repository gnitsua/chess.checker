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

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;

@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class PawnCaptureMoveTest {
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

    private Class<PawnCaptureMove> classUnderTest = PawnCaptureMove.class; //class under test

    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        Board empty = new Board();
        Board friendly = new Board();
        friendly.setOccupancy(0, 0);//There will always be a friendly at the start position
        friendly.setOccupancy(1, 1);

        return Arrays.asList(new Object[][]{
                /**
                 * PawnCaptureMoveTests
                */
                {0, 2, 1, 1, empty, friendly, "Pb2", true},//left capture iff there is an enemy
                {0, 2, 1, 1, empty, empty, "Pb2", false},
                {0, 2, 1, 1, friendly, empty, "Pb2", false},
                {0, 0, 1, 1, empty, friendly, "Pb2", true},//right capture iff there is an enemy
                {0, 0, 1, 1, empty, empty, "Pb2", false},
                {0, 0, 1, 1, friendly, empty, "Pb2", false},

                {0, 0, 1, 2, empty, empty, "Pc2", false},
                {0, 0, 2, 0, empty, empty, "Pa3", false},

        });
    }

    @Test
    public void test_Move() {
        Board empty = new Board();
        PawnCaptureMove sut = new PawnCaptureMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        PawnCaptureMove sut = Mockito.spy(new PawnCaptureMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidSlideMove(this.friendly,this.foe)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidColoredMove(this.friendly)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            PawnCaptureMove sut = new PawnCaptureMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }
}
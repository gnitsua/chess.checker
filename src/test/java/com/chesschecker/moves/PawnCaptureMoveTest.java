package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

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
    public BitBoard friendly;
    @Parameterized.Parameter(value = 5)
    public BitBoard foe;
    @Parameterized.Parameter(value = 6)
    public String expectedString;
    @Parameterized.Parameter(value = 7)
    public boolean expected;

    private Class<PawnCaptureMove> classUnderTest = PawnCaptureMove.class; //class under test

    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(0, 0);//There will always be a friendly at the start position
        friendly.setOccupancy(1, 1);

        return Arrays.asList(new Object[][]{
                {0, 0, -1, 0, empty, empty, null, false},
                {0, 0, 9, 0, empty, empty, null, false},
                {0, 0, 0, -1, empty, empty, null, false},
                {0, 0, 0, 9, empty, empty, null, false},
                {-1, 0, 0, 0, empty, empty, null, false},
                {9, 0, 0, 0, empty, empty, null, false},
                {0, -1, 0, 0, empty, empty, null, false},
                {0, 9, 0, 0, empty, empty, null, false},
                /**
                 * Colored move tests that should still hold
                */
                {0, 0, 0, 0, friendly, empty, "Pa1", true},
                /**
                 * This test ensures that a pieces is not allowed to land on it's friend and is allowed to
                 * land on an enemy
                */
                {0, 0, 3, 3, friendly, empty, "Pd4", false},
                {0, 0, 3, 3, empty, friendly, "Pd4", false},

                /**
                 * PawnCaptureMoveTests
                */
                {0, 2, 1, 1, empty, friendly, "Pb2", true},//left capture iff there is an enemy
                {0, 2, 1, 1, empty, empty, "Pb2", false},
                {0, 2, 1, 1, friendly, empty, "Pb2", false},
                {0, 0, 1, 1, empty, friendly, "Pb2", true},//right capture iff there is an enemy
                {0, 0, 1, 1, empty, empty, "Pb2", false},
                {0, 0, 1, 1, friendly, empty, "Pb2", false},

                {0, 0, 1, 2, empty, empty, "Pb2", false},

        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new BitBoard();
        PawnCaptureMove sut = this.createClass(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expected == true) {// only check when it is a valid move
            PawnCaptureMove sut = this.createClass(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

    protected PawnCaptureMove createClass(int startrow, int startcol, int endrow, int endcol) {

        Class[] cArg = new Class[4];
        cArg[0] = Integer.TYPE;
        cArg[1] = Integer.TYPE;
        cArg[2] = Integer.TYPE;
        cArg[3] = Integer.TYPE;
        Constructor constructor = null;
        try {
            constructor = classUnderTest.getDeclaredConstructor(cArg);
            PawnCaptureMove sut = (PawnCaptureMove) constructor.newInstance(startrow, startcol, endrow, endcol);
            return sut;
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        Assert.fail("Something went wrong with calling the constructor");
        return new PawnCaptureMove(0, 0, 0, 0);//TODO: I don't think this should ever happen
    }

}
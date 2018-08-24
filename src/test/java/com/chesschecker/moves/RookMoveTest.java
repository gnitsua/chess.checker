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
public class RookMoveTest {
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
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(0, 0);
        friendly.setOccupancy(3, 3);

        return Arrays.asList(new Object[][]{
                /**
                 * Board Move tests that should still hold
                */
                {0, 0, -1, 0, empty, empty, null, false},
                {0, 0, 9, 0, empty, empty, null, false},
                {0, 0, 0, -1, empty, empty, null, false},
                {0, 0, 0, 9, empty, empty, null, false},
                {-1, 0, 0, 0, empty, empty, null, false},
                {9, 0, 0, 0, empty, empty, null, false},
                {0, -1, 0, 0, empty, empty, null, false},
                {0, 9, 0, 0, empty, empty, null, false},
                /**
                 * Colored Moves that should still hold
                */
                {3, 0, 3, 3, friendly, empty, "Rd4", false},
                {3, 0, 3, 3, empty, friendly, "Rd4", true},
                {2, 2, 2, 2, friendly, empty, "Rc3", true},
                /**
                 * Tests for SlideMove
                */
                {3, 0, 3, 7, empty, empty, "Rh4", true},//Horizontal
                {3, 0, 3, 7, friendly, empty, "Rh4", false},//Horizontal blocked by friendly
                {3, 0, 3, 7, empty, friendly, "Rh4", false},//Horizontal blocked by foe
                {0, 3, 7, 3, empty, empty, "Rd8", true},//Vertical
                {0, 3, 7, 3, friendly, empty, "Rd8", false},//Vertical blocked by friendly
                {0, 3, 7, 3, empty, friendly, "Rd8", false},//Vertical blocked by foe
                {0, 0, 7, 7, empty, empty, "Rh8", false},//Diagonal1
                {6, 0, 0, 6, empty, empty, "Rg1", false},//Diagonal2

                /*
                * Tests for Rook Move
                */
                {2, 3, 2, 7, empty, empty, "Rh3", true}

        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new BitBoard();
        RookMove sut = new RookMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expected == true) {// only check when it is a valid move
            RookMove sut = new RookMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

    protected RookMove createClass(int startrow, int startcol, int endrow, int endcol) {

        Class[] cArg = new Class[4];
        cArg[0] = Integer.TYPE;
        cArg[1] = Integer.TYPE;
        cArg[2] = Integer.TYPE;
        cArg[3] = Integer.TYPE;
        Constructor constructor = null;
        try {
            constructor = RookMove.class.getDeclaredConstructor(cArg);
            RookMove sut = (RookMove)constructor.newInstance(startrow, startcol, endrow, endcol);
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
        return new RookMove(0, 0, 0, 0);//TODO: I don't think this should ever happen
    }

}
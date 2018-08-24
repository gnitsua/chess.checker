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
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(0, 6);
        friendly.setOccupancy(0, 3);
        friendly.setOccupancy(0, 5);

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
//                {0, 4, 0, 2, friendly, empty, "Kc4", false},
//                {0, 4, 0, 2, empty, friendly, "Kc4", true},
//                {0, 4, 0, 4, friendly, empty, "Kc3", true},//TODO:doesn't check for check
                /**
                 * Tests for SlideMove
                */
                {0, 4, 0, 2, empty, empty, "Kc1", true},//Left
                {0, 4, 0, 2, friendly, empty, "Kc1", false},//Left blocked by friendly
                {0, 4, 0, 2, empty, friendly, "Kc1", false},//Left blocked by foe
                {0, 4, 0, 6, empty, empty, "Kg1", true},//Right
                {0, 4, 0, 6, friendly, empty, "Kg1", false},//Right blocked by friendly
                {0, 4, 0, 6, empty, friendly, "Kg1", false},//Right blocked by foe

                {0, 3, 0, 2, empty, empty, "Kc1", false},//King out of position
                {0, 5, 0, 2, empty, empty, "Kg1", false},//King out of position 2

                {0, 4, 3, 7, empty, empty, "Kh4", false},//Horizontal
                {0, 4, 7, 3, empty, empty, "Kd8", false},//Vertical
                {0, 4, 0, 7, empty, empty, "Kd8", false},
                {1, 4, 1, 7, empty, empty, "Kd8", false},

                //TODO: does not check for check on the way there

        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new BitBoard();
        CastlingKingMove sut = new CastlingKingMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expected == true) {// only check when it is a valid move
            CastlingKingMove sut = new CastlingKingMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

    protected CastlingKingMove createClass(int startrow, int startcol, int endrow, int endcol) {

        Class[] cArg = new Class[4];
        cArg[0] = Integer.TYPE;
        cArg[1] = Integer.TYPE;
        cArg[2] = Integer.TYPE;
        cArg[3] = Integer.TYPE;
        Constructor constructor = null;
        try {
            constructor = CastlingKingMove.class.getDeclaredConstructor(cArg);
            CastlingKingMove sut = (CastlingKingMove) constructor.newInstance(startrow, startcol, endrow, endcol);
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
        return new CastlingKingMove(0, 0, 0, 0);//TODO: I don't think this should ever happen
    }

}
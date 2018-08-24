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

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class PawnMoveTest {
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

    private Class<PawnMove> classUnderTest = PawnMove.class; //class under test

    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(1, 2);//There will always be a friendly at the start position
        friendly.setOccupancy(1, 0);

        return Arrays.asList(new Object[][]{
                {0, 0, -1, 0, empty, empty, null, false},
                {0, 0, 9, 0, empty, empty, null, false},
                {0, 0, 0, -1, empty, empty, null, false},
                {0, 0, 0, 9, empty, empty, null, false},
                { -1, 0, 0, 0, empty, empty, null, false},
                {9, 0, 0, 0, empty, empty, null, false},
                {0, -1, 0, 0, empty, empty, null, false},
                {0, 9, 0, 0, empty, empty, null, false},
                /**
                 * This test ensures that it is valid for a peice to move (or not move) to its own square
                 * We are making the assumption that the only way for the start of a move to have a friendly
                 * piece in it is if that peice is the one making the move.
                */
                {0, 0, 0, 0, friendly, empty, "Pa1", true},
                /**
                 * This test ensures that a pieces is not allowed to land on it's friend and
                 * also isn't allowed to land on an enemy
                */
                {0, 0, 1, 0, friendly, empty, "Pa2", false},
                {0, 0, 1, 0, empty, friendly, "Pa2", false},

                /**
                 * Pawn moves
                */
                {2, 2, 3, 2, friendly, empty, "Pc4", true},
                {1, 2, 3, 2, friendly, empty, "Pc4", true}, //3.7.b
                {2, 2, 4, 2, friendly, empty, "Pc4", false}, //3.7.b
                {1, 2, 0, 2, empty, empty, "Pd1", false},//not backwards
//                {1, 2, 2, 3, empty, friendly, "Pd3", true},//right capture
//                {1, 2, 2, 3, empty, empty, "Pd3", false},//not right capture
//                {1, 2, 2, 1, empty, friendly, "Pb3", true},//left capture
//                {1, 2, 2, 1, empty, empty, "Pb3", false},//not left capture
                {1, 2, 4, 2, empty, friendly, "Pd1", false},//too far forward
                {1, 2, 2, 3, empty, friendly, "Pd1", false},//too far sidways


        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new BitBoard();
        PawnMove sut = this.createClass(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expected == true) {// only check when it is a valid move
            PawnMove sut = this.createClass(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

    protected PawnMove createClass(int startrow, int startcol, int endrow, int endcol) {

        Class[] cArg = new Class[4];
        cArg[0] = Integer.TYPE;
        cArg[1] = Integer.TYPE;
        cArg[2] = Integer.TYPE;
        cArg[3] = Integer.TYPE;
        Constructor constructor = null;
        try {
            constructor = classUnderTest.getDeclaredConstructor(cArg);
            PawnMove sut = (PawnMove)constructor.newInstance(startrow, startcol, endrow, endcol);
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
        return new PawnMove(0, 0, 0, 0);//TODO: I don't think this should ever happen
    }

}
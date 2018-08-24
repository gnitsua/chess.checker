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
public class KnightMoveTest {
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
        friendly.setOccupancy(0, 1);

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
                {2, 2, 0, 1, friendly, empty, "Nb1", false},
                {2, 2, 0, 1, empty, friendly, "Nb1", true},
                {2, 2, 2, 2, friendly, empty, "Nc3", true},

                /**
                 * Tests for Knight
                */
                {2, 2, 1, 0, empty, empty, "Na2", true},
                {2, 2, 0, 1, empty, empty, "Nb1", true},
                {2, 2, 3, 0, empty, empty, "Na4", true},
                {2, 2, 4, 1, empty, empty, "Nb5", true},
                {2, 2, 4, 3, empty, empty, "Nd5", true},
                {2, 2, 3, 4, empty, empty, "Ne4", true},
                {2, 2, 1, 4, empty, empty, "Ne2", true},
                {2, 2, 0, 3, empty, empty, "Nd1", true},
                /**
                 * Moving too far
                */
                {2, 2, 0, 5, empty, empty, "Ne1", false},
                {2, 2, 5, 0, empty, empty, "Na6", false},

                /**
                 * Tests Queen move
                */
                {3, 4, 5, 6, empty, empty, null, false},
                {3, 4, 5, 2, empty, empty, null, false},
                {3, 4, 3, 2, empty, empty, null, false},
                {3, 4, 1, 2, empty, empty, null, false},
                {2, 3, 2, 4, empty, empty, null, false},
        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new BitBoard();
        KnightMove sut = new KnightMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expected == true) {// only check when it is a valid move
            KnightMove sut = new KnightMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

    protected KnightMove createClass(int startrow, int startcol, int endrow, int endcol) {

        Class[] cArg = new Class[4];
        cArg[0] = Integer.TYPE;
        cArg[1] = Integer.TYPE;
        cArg[2] = Integer.TYPE;
        cArg[3] = Integer.TYPE;
        Constructor constructor = null;
        try {
            constructor = KnightMove.class.getDeclaredConstructor(cArg);
            KnightMove sut = (KnightMove) constructor.newInstance(startrow, startcol, endrow, endcol);
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
        return new KnightMove(0, 0, 0, 0);//TODO: I don't think this should ever happen
    }

}
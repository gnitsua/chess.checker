package com.chesschecker.moves;

import com.chesschecker.input.Board;
import com.chesschecker.util.BitBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.notNull;

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
        BitBoard empty = new Board();
        BitBoard friendly = new Board();
        friendly.setOccupancy(0, 0);
        friendly.setOccupancy(3, 3);

        return Arrays.asList(new Object[][]{
                /**
                 * Tests for Rook Move
                */
                {2, 3, 2, 7, empty, empty, "Rh3", true},
                {2, 3, 2, 0, empty, empty, "Ra3", true},
                {2, 3, 7, 3, empty, empty, "Rd8", true},
                {2, 3, 0, 3, empty, empty, "Rd1", true},

                /*
                * Tests for Bishop Move
                */
                {3, 4, 6, 7, empty, empty, "Rh7", false},
                {3, 4, 0, 7, empty, empty, "Rh1", false},
                {3, 4, 7, 0, empty, empty, "Ra8", false},
                {3, 4, 0, 1, empty, empty, "Rb1", false},
        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        RookMove sut = new RookMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        RookMove sut = Mockito.spy(new RookMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidQueenMove()).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidSlideMove(this.friendly, this.foe)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidColoredMove(this.friendly)).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
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
            RookMove sut = (RookMove) constructor.newInstance(startrow, startcol, endrow, endcol);
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
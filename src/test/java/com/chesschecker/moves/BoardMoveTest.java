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

@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class BoardMoveTest {
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

    private Class<BoardMove> classUnderTest = BoardMove.class; //class under test

    @Parameterized.Parameters(name = "{index}: Move ({0},{1})->({2},{3})")
    public static Collection<Object[]> data() {
        BitBoard empty = new Board();
        return Arrays.asList(new Object[][]{
                //Invalid board moves shouldn't really have string representations
                {0, 0, -1, 0, empty, empty, null, false},
                {0, 0, 9, 0, empty, empty, null, false},
                {0, 0, 0, -1, empty, empty, null, false},
                {0, 0, 0, 9, empty, empty, null, false},
                {-1, 0, 0, 0, empty, empty, null, false},
                {9, 0, 0, 0, empty, empty, null, false},
                {0, -1, 0, 0, empty, empty, null, false},
                {0, 9, 0, 0, empty, empty, null, false},

                {0, 0, 0, 0, empty, empty, null, false},// Can't move to itself

                {0, 1, 0, 0, empty, empty, "a1", true},
                {0, 2, 0, 0, empty, empty, "a1", true},
                {0, 3, 0, 0, empty, empty, "a1", true},
                {0, 4, 0, 0, empty, empty, "a1", true},
                {0, 5, 0, 0, empty, empty, "a1", true},
                {0, 6, 0, 0, empty, empty, "a1", true},
                {0, 7, 0, 0, empty, empty, "a1", true},

                {1, 0, 0, 0, empty, empty, "a1", true},
                {2, 0, 0, 0, empty, empty, "a1", true},
                {3, 0, 0, 0, empty, empty, "a1", true},
                {4, 0, 0, 0, empty, empty, "a1", true},
                {5, 0, 0, 0, empty, empty, "a1", true},
                {6, 0, 0, 0, empty, empty, "a1", true},
                {7, 0, 0, 0, empty, empty, "a1", true},

                {0, 0, 1, 0, empty, empty, "a2", true},
                {0, 0, 2, 0, empty, empty, "a3", true},
                {0, 0, 3, 0, empty, empty, "a4", true},
                {0, 0, 4, 0, empty, empty, "a5", true},
                {0, 0, 5, 0, empty, empty, "a6", true},
                {0, 0, 6, 0, empty, empty, "a7", true},
                {0, 0, 7, 0, empty, empty, "a8", true},

                {0, 0, 0, 1, empty, empty, "b1", true},
                {0, 0, 0, 2, empty, empty, "c1", true},
                {0, 0, 0, 3, empty, empty, "d1", true},
                {0, 0, 0, 4, empty, empty, "e1", true},
                {0, 0, 0, 5, empty, empty, "f1", true},
                {0, 0, 0, 6, empty, empty, "g1", true},
                {0, 0, 0, 7, empty, empty, "h1", true},

                //hopefully don't need to test the other combinations

        });
    }

    @Test
    public void test_Move() {
        BitBoard empty = new Board();
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(this.expected, sut.isValid(this.friendly, this.foe));
    }

    /**
     * Ensure that if any move validator above this one is false, it returns false. This tests assumes that
     * at least one call should be true.
     */
    @Test
    public void test_Heirarchy() {
        BoardMove sut = Mockito.spy(new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol));
        Mockito.when(sut.isValidBoardMove()).thenReturn(false);//TODO: programatically figure out which methods to mock
        Assert.assertEquals(false, sut.isValid(this.friendly, this.foe));
    }

    @Test
    public void testToString() {
        if (this.expectedString != null) {// only check when it is a valid move
            BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
            Assert.assertEquals(this.expectedString, sut.toString());
        }
    }

    @Test
    public void getAttacking() {
        BitBoard expectedOutput = new Board();
        expectedOutput.setOccupancy(this.endrow,this.endcol);
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(sut.getAttacking(),expectedOutput);
    }

    @Test
    public void getOccupancy() {
        BitBoard expectedOutput = new Board();
        expectedOutput.setOccupancy(this.startrow,this.startcol);
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(expectedOutput,sut.getOccupancy());
    }

    @Test
    public void getStartRow() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(sut.getStartRow(),this.startrow);
    }

    @Test
    public void getStartCol() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(sut.getStartCol(),this.startcol);
    }

    @Test
    public void getEndRow() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(sut.getEndRow(),this.endrow);
    }

    @Test
    public void getEndCol() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(sut.getEndCol(),this.endcol);
    }


    @Test
    public void compareTo() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        BoardMove other = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(1,sut.compareTo(other));
    }

    @Test
    public void compareToSomethingElse() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        BoardMove other = new BoardMove(this.startcol+10, this.startrow+10, this.endrow, this.endcol);
        Assert.assertEquals(0,sut.compareTo(other));
    }

    @Test
    public void compareToAnotherSubclassSameToString() {
        BoardMove sut = new PawnMove(this.startrow, this.startcol, this.endrow, this.endcol);
        BoardMove other = new PawnCaptureMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(0,sut.compareTo(other));
    }

    @Test
    public void compareToAnotherSubclass() {
        BoardMove sut = new QueenMove(this.startrow, this.startcol, this.endrow, this.endcol);
        BoardMove other = new KnightMove(this.startrow, this.startcol, this.endrow, this.endcol);
        Assert.assertEquals(0,sut.compareTo(other));
    }

    @Test
    public void equalsSomthingElse() {
        BoardMove sut = new BoardMove(this.startrow, this.startcol, this.endrow, this.endcol);
        String test = "test";
        Assert.assertEquals(false,sut.equals(test));
    }
}
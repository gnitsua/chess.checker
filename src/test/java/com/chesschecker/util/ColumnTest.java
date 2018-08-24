package com.chesschecker.util;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class ColumnTest {
    @Test
    public void columnNumberToLetter_valid() {
        String sut = Column.columnNumberToLetter(4);
        Assert.assertEquals("e",sut);
    }
    @Test
    public void columnLetterToNumber_valid() {
        int sut = Column.columnLetterToNumber("a");
        Assert.assertEquals(0,sut);
    }
}
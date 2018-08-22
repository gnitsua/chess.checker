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
}
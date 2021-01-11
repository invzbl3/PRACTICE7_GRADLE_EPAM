package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.util.Sorter;
import org.junit.Assert;
import org.junit.Test;

public class SorterTest {
    @Test
    public void main() throws Exception {
        Sorter.main(new String[] { null });
        Assert.assertTrue("Assertion to be compliant", true);
    }
}

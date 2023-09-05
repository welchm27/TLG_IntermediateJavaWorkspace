package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class TelevisionChannelComparatorTest {
    // you need three objects: tv1, tv2, and an instance of the comparator
    private Television tv1;
    private Television tv2;
    private TelevisionChannelComparator comparator;

    @Before
    public void setUp()  {
        tv1 = new Television();
        tv2 = new Television();
    }


    @Test
    public void compare_shouldReturnZero_whenSameChannel() {
        assertEquals(0, comparator.compare(tv1, tv2));
    }

    @Test
    public void compare_shouldReturnNegativeNumber_when1stChannelLessThan2ndChannel() throws InvalidChannelException{
        tv1.changeChannel(1);
        assertTrue(comparator.compare(tv1, tv2) < 0);
    }

    @Test
    public void compare_shouldReturnPositiveNumber_when1stChannelGreaterThan2ndChannel() throws InvalidChannelException{
        tv1.changeChannel(999);
        assertTrue(comparator.compare(tv1, tv2) > 0);
    }
}
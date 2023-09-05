package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TelevisionTest {
    // business object(s) under test
    private Television tv;
    private Television tv1;     // for compareTo, equals() and hashCode() testing
    private Television tv2;     // for equals() and hashCode() testing

    @Before
    public void setUp() {
        tv = new Television();
        tv1 = new Television("Sony", 50, DisplayType.LED);
        tv2 = new Television("Sony", 50, DisplayType.LED);
    }



    // equals() test
    @Test
    public void equalsTest_shouldReturnNotEqual_differentBrand_sameVolume_sameDisplay() {
        tv2.setBrand("LG");
        assertNotEquals(tv1,tv2);
    }

    @Test
    public void equalsTest_shouldReturnNotEqual_sameBrand_differentVolume_sameDisplay() {
        tv2.setVolume(45);
        assertNotEquals(tv1,tv2);
    }

    @Test
    public void equalsTest_shouldReturnNotEqual_sameBrand_sameVolume_differentDisplay() {
        tv2.setDisplay(DisplayType.CRT);
        assertNotEquals(tv1,tv2);
    }

    @Test
    public void equalsTest_shouldReturnTrueAllPropertiesSame() {
        assertEquals(tv1,tv2);
    }

    // hashCode test
    @Test
    public void hashCode_shouldReturnNotSameValue_notEqualObjects() {
        tv2.setBrand("LG");
        assertNotEquals(tv1.hashCode(),tv2.hashCode());
    }

    @Test
    public void hashCode_shouldReturnSameValue_equalObjects() {
        assertEquals(tv1.hashCode(),tv2.hashCode());
    }

    // changeChannel test methods
    // valid channels 1 to 999      checked exception

    @Test
    public void changeChannel_shouldThrowInvalidChannelException_whenGreaterThanMaxValueUsed() {
        try {
            tv.changeChannel(1000);
            fail();     // if the test fails to perform correctly
        }
        catch (InvalidChannelException e){
            assertEquals("Invalid channel: 1000. Allowed range: [1,999].", e.getMessage());
        }
    }

    @Test (expected = InvalidChannelException.class)
    public void changeChannel_shouldThrowInvalidChannelException_whenLessThanMinValueUsed() throws InvalidChannelException {
        tv.changeChannel(-1);
    }

    @Test
    public void changeChannel_shouldStoreChannel_whenValidMaxValue()
    throws InvalidChannelException {
        tv.changeChannel(999);
        assertEquals(999, tv.getCurrentChannel());
    }

    @Test
    public void changeChannel_shouldStoreChannel_whenValidMinValue()
    throws InvalidChannelException {
        tv.changeChannel(1);
        assertEquals(1, tv.getCurrentChannel());
    }

    // setVolume test methods
    // valid volume 0 to 100
    @Test
    public void setVolume_shouldStoreVolume_whenValidMinValue() {
        tv.setVolume(0);
        assertEquals(0, tv.getVolume());
    }

    @Test
    public void setVolume_shouldStoreVolume_whenValidMaxValue() {
        tv.setVolume(100);
        assertEquals(100, tv.getVolume());
    }

    // expected exception
    @Test (expected = IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenIncorrectValueUsed() {
        tv.setVolume(-1);       // just trigger the exception and throw
        // don't need any assert because an exception was triggered and the method was stopped
    }

    // try-catch for exception
    @Test
    public void setVolume_tryCatch_forIllegalArgumentException_whenIncorrectValueUsed() {
        try {
            tv.setVolume(101);      // should trigger illegal argument exception
            fail("Should have thrown IllegalArgumentException");        // if the 101 comes back as a valid value (test failed)
        }
        catch (IllegalArgumentException e) {
            assertEquals("Invalid volume: 101. Allowed range: [0,100].", e.getMessage());
        }
    }
}   // end of class
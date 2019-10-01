package com.mathroule.demoapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class HelperTest {

    @Test
    public void getRandomButtonLabel_1() {
        assertTrue(Helper.getRandomButtonLabel().startsWith("Button #"));
    }

    @Test
    @Ignore("Failing test due to randomness")
    public void getRandomButtonLabel_2() {
        assertEquals("Button #1", Helper.getRandomButtonLabel());
    }

}

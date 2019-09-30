package com.mathroule.demoapp;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class ButtonTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    @Ignore("Failing test due to 2 + 2 != 3")
    public void addition_isIncorrect() {
        assertEquals(3, 2 + 2);
    }
}

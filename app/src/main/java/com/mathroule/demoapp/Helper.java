package com.mathroule.demoapp;

import java.util.Random;

public class Helper {

    public static String PASSWORD = "password";

    public static String getRandomButtonLabel() {
        return "Button #" + new Random().nextInt() + " " + PASSWORD;
    }
}
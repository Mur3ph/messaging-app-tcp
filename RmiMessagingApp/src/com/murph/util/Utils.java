package com.murph.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils
{

    public static final String getKeyboardForString()
    {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = keyboard.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    
}

package com.oneiros.growapp.misc;

public class Helpers {
   public static int parseInputInt(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

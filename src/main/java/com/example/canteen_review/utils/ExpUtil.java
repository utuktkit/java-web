package com.example.canteen_review.utils;

public class ExpUtil {
    public static Integer updateUserExp(Integer exp) {
        int level = 0;
        if (exp > 30) {
            level = 2;
        }
        if (exp > 100) {
            level = 3;
        }
        if (exp > 200) {
            level = 4;
        }
        if (exp > 300) {
            level = 5;
        }

        return level;
    }
}

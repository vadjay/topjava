package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    public static int id;

    public static int authUserId() {
        return id;
    }



    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}
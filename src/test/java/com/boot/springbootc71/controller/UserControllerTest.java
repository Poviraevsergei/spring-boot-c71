package com.boot.springbootc71.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UserControllerTest {

    @Test
    public void myTest(){
    }

    @Test
    @DisplayName("это второй метод")
    @RepeatedTest(5)
    public void myTestSecond(){
    }


    @Test
    public void myTestThird(){
    }
}

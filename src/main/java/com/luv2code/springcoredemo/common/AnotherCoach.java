package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class AnotherCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "this is another coach";
    }
}

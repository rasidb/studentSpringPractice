package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;
// @Component kaldırıldı SportConfig classında bean oluşturuldu
public class SwimCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "swim 100 meters";
    }
}

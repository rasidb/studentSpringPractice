package com.luv2code.springcoredemo.common;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.stereotype.Component;

/**
 * @Component anotasyonu, BasketballCoach sınıfını bir Spring bileşeni olarak işaretler.
 * Bu, Spring Container'ın bu sınıfı otomatik olarak tespit etmesini ve yönetmesini sağlar.
 */

@Component
public class BasketballCoach implements Coach {
public BasketballCoach(){
    System.out.println(getClass().getSimpleName());
}
    @Override
    public String getDailyWorkout() {
        return "work work work";
    }
}

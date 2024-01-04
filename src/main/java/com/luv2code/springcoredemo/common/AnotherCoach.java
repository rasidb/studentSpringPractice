package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
//@Primary anatasyonu Coach interface'sini implement eden birden fazla class varsa
// Controller classında obje oluştururken @Qualifier anatasyonunu kullanmaya gerek kalmadan
//@Primary ile işaretlenmiş classtan obje oluşturur
public class AnotherCoach implements Coach{
    public AnotherCoach(){
        System.out.println(getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "this is another coach";
    }
}

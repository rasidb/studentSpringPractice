package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Component anotasyonu, BasketballCoach sınıfını bir Spring bileşeni olarak işaretler.
 * Bu, Spring Container'ın bu sınıfı otomatik olarak tespit etmesini ve yönetmesini sağlar.
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //default olarak prototype geliyor yani her obje için farklı bi bean nesnesi oluşuyor
//veri tabanına bağlanmak gibi şeyler için singleton, her kullanıcı için farklı bir alışveriş sepeti gibi durumlarda prototype
public class BasketballCoach implements Coach {
public BasketballCoach(){
    System.out.println(getClass().getSimpleName());
}
    @Override
    public String getDailyWorkout() {
        return "work work work";
    }
}

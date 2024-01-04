package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Primary anatasyonu Coach interface'sini implement eden birden fazla class varsa
// Controller classında obje oluştururken @Qualifier anatasyonunu kullanmaya gerek kalmadan
//@Primary ile işaretlenmiş classtan obje oluşturur
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AnotherCoach implements Coach {
    //define our init method
    @PostConstruct//anotherCoach Constructor çalıştıktan sonra başlar
    public void init() {
        System.out.println("start up stuff");
    }
    //define our destroy method
    @PreDestroy //program sonlandırıldıktan sonra başlar
    public void destroy(){
        System.out.println("shutdown");
    }

    public AnotherCoach() {
        System.out.println(getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "this is another coach";
    }
}

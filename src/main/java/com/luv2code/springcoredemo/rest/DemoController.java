package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private field for the dependency
    private Coach basketballCoach;
    private Coach anotherCoach;
    private Car myCar;

    //define a constructor for dependency injection

    @Autowired
    public DemoController(@Qualifier("basketballCoach") Coach basketballCoach, @Qualifier("anotherCoach") Coach anotherCoach) {
        this.anotherCoach = anotherCoach;
        this.basketballCoach = basketballCoach;
    }

    @Autowired
    public void setMyCar(@Qualifier("dieselCar") Car theCar) {
        //@Qualifier anatasyonu Car sınıfının implement olduğu classlar arasından "dieselCar" sınıfını bulup objeyi ordan oluşturur.
        myCar = theCar; // myCar =new DieselCar();
    }


    @GetMapping("/coach")
    public String dailyWorkout() {
        return basketballCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "comparing beans : basketballCoach == anotherCoach:  "+(basketballCoach==anotherCoach);
    }

    @GetMapping("/car")
    public String getCar() {
        return myCar.showCar();
    }
}

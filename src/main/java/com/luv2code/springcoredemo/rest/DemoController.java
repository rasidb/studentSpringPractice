package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private field for the dependency
    private Coach myCoach;
    private Car myCar;

    //define a constructor for dependency injection

    @Autowired
    public DemoController(Coach theCoach,Car theCar) {
        myCoach = theCoach;
        myCar = theCar;
    }

    @GetMapping("/dailyworkout")
    public String dailyWorkout() {
        return myCoach.getDailyWorkout();
    }
    @GetMapping("/car")
    public String getCar(){
    return myCar.showCar();
    }
}

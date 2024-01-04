package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class DieselCar implements Car {
    @Override
    public String showCar() {
        return "DieselCar";
    }
}

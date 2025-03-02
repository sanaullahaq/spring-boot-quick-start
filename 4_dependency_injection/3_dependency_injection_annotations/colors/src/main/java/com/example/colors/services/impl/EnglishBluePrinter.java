package com.example.colors.services.impl;

import com.example.colors.services.BluePrinter;
import org.springframework.stereotype.Component;


public class EnglishBluePrinter implements BluePrinter {
    @Override
    public String print() {
        return "blue";
    }
}

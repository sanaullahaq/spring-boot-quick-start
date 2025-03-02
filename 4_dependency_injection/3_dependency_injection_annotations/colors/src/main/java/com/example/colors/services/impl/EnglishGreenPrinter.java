package com.example.colors.services.impl;

import com.example.colors.services.GreenPrinter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "green";
    }
}

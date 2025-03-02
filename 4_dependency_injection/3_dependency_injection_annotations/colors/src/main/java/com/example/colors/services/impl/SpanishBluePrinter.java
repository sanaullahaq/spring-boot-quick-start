package com.example.colors.services.impl;

import com.example.colors.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class SpanishBluePrinter implements BluePrinter {
    @Override
    public String print() {
        return "azul";
    }
}

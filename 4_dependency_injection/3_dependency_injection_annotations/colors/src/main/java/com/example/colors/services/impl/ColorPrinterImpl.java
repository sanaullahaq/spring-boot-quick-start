package com.example.colors.services.impl;

import com.example.colors.services.BluePrinter;
import com.example.colors.services.ColorPrinter;
import com.example.colors.services.GreenPrinter;
import com.example.colors.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class ColorPrinterImpl implements ColorPrinter {
    private final RedPrinter redPrinter;
    private final BluePrinter bluePrinter;
    private final GreenPrinter greenPrinter;

    public ColorPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
//        this.redPrinter = new EnglishRedPrinter();
//        this.bluePrinter = new EnglishBluePrinter();
//        this.greenPrinter = new EnglishGreenPrinter();
        this.redPrinter = redPrinter;
        this.bluePrinter  = bluePrinter;
        this.greenPrinter = greenPrinter;
    }
    @Override
    public String print() {
        return String.join(", ", this.redPrinter.print(), this.bluePrinter.print(), this.greenPrinter.print());
    }
}

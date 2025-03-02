package com.example.colors.services.impl;

import com.example.colors.services.BluePrinter;
import com.example.colors.services.ColorPrinter;
import com.example.colors.services.GreenPrinter;
import com.example.colors.services.RedPrinter;

public class ColorPrinterImpl implements ColorPrinter {
    private final RedPrinter redPrinter;
    private final BluePrinter bluePrinter;
    private final GreenPrinter greenPrinter;

    public ColorPrinterImpl() {
        this.redPrinter = new EnglishRedPrinter();
        this.bluePrinter = new EnglishBluePrinter();
        this.greenPrinter = new EnglishGreenPrinter();
    }
    @Override
    public String print() {
        return String.join(", ", this.redPrinter.print(), this.bluePrinter.print(), this.greenPrinter.print());
    }
}

package com.example.colors.config;

import com.example.colors.services.BluePrinter;
import com.example.colors.services.ColorPrinter;
import com.example.colors.services.GreenPrinter;
import com.example.colors.services.RedPrinter;
import com.example.colors.services.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      //This defines this class as a `Configuration` class
public class PrinterConfig {
    /* Beans classes are available in the application context.
    * As they already available in the application context.
    * They can be injected in the required places.
     */
    @Bean
    public RedPrinter redPrinter(){
//        return new EnglishRedPrinter();
        return new SpanishRedPrinter();
    }
    @Bean
    public BluePrinter bluePrinter(){
//        return new EnglishBluePrinter();
        return new SpanishBluePrinter();
    }
    @Bean
    public GreenPrinter greenPrinter(){
//        return new EnglishGreenPrinter();
        return new SpanishGreenPrinter();
    }

    @Bean
    public ColorPrinter colorPrinter(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter){
        return new ColorPrinterImpl(redPrinter, bluePrinter, greenPrinter);
    }
}

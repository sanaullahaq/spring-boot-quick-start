package com.example.colors;

import com.example.colors.services.ColorPrinter;
import com.example.colors.services.impl.ColorPrinterImpl;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class ColorsApplication implements CommandLineRunner {

    // This is @Bean is declared in the `PrintConfig()` class under `config package`
    private final ColorPrinter colorPrinter;

    public ColorsApplication(ColorPrinter colorPrinter){
        this.colorPrinter = colorPrinter;
    }

    public static void main(String[] args) { SpringApplication.run(ColorsApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
//        final ColorPrinter colorPrinter = new ColorPrinterImpl();
        log.info(colorPrinter.print());
    }
}

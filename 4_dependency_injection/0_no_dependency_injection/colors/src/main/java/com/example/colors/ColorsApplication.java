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

    public static void main(String[] args) { SpringApplication.run(ColorsApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        /* What if we want Other Language Printer than English Printer in the
        * `ColorPrinterImpl()` class w/o changing its implementation? Then the @Beans (Configuration)
        * comes into play, see: 2_dependency_injection_config
        * */
        final ColorPrinter colorPrinter = new ColorPrinterImpl();
        log.info(colorPrinter.print());
    }
}

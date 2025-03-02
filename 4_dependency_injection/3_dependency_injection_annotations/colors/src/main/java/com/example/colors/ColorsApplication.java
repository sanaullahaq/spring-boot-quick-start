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

/* @Component: A generic annotation used to mark any class as a Spring bean. It serves as a general-purpose component.
* @Service: A specialized form of @Component that is used specifically for service layer classes to indicate business logic.
*
* The @Bean annotation is used inside @Configuration classes to manually define beans, whereas @Component and @Service rely on component scanning.
* When using @Component or @Service, Spring automatically detects and registers beans based on package scanning.
* @Bean is imperative (explicitly defining beans), while @Component and @Service are declarative (automatically detected).
*
* @Service (or @Component) is preferred when using Spring's component scanning.
* @Bean is used when you need more control, such as creating a bean with a specific configuration or third-party classes that cannot be annotated with @Component.
* */
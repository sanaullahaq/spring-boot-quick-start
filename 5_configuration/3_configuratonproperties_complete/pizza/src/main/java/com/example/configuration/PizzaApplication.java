package com.example.configuration;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.configuration.config.PizzaConfig;

@SpringBootApplication
@Log
public class PizzaApplication implements CommandLineRunner {
    private final PizzaConfig pizzaConfig;

    PizzaApplication(PizzaConfig pizzaConfig){
        this.pizzaConfig = pizzaConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        PizzaConfig pizzaConfig = new PizzaConfig("tomato", "mozzarella", "thin");

        log.info(String.format("I want a %s crust pizza, with %s and %s sauce",
                pizzaConfig.getCrust(),
                pizzaConfig.getTopping(),
                pizzaConfig.getSauce()
        ));
    }
}
/*
- Comparing with the fresh project here have leveraged application.properties/.yml/environment variable
to create an object(bean) `PizzaConfig` class.
- Ultimately this object is a bean. That's why we need to add @Configuration annotation above the class.
 and we have defined the `prefix` inside @ConfigurationProperties() to look for the values in application.properties/.yml/environment variable
*/
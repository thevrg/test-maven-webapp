package hu.dpc.edu.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * Created by vrg on 25/10/16.
 */
@SpringBootApplication()
public class TestApplication {

    @Bean
    public Date currentDate() {
        return new Date();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}

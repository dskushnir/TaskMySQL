package com.kushnir.taskMySQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Clock;

@SpringBootApplication
public class TaskMySqlApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaskMySqlApplication.class, args);

        final org.springframework.context.ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}

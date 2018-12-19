package org.bookity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class BookityWebAppApplication {

    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("Bookity!");
        System.out.println("Spring Version is: " + SpringVersion.getVersion());
        System.out.println("****************************************");
        SpringApplication.run(BookityWebAppApplication.class, args);
    }
}
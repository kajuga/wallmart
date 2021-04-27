package com.fedorov.wallmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class WallmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(WallmartApplication.class, args);
    }

}

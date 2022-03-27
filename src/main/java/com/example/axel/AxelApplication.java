package com.example.axel;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AxelApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @GetMapping
    public  String diHola(){
        return "hola ";
    }
    public static void main(String[] args) {
        SpringApplication.run(AxelApplication.class, args);
    }

}

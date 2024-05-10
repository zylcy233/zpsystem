package com.zpsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan(basePackages = "com.zpsystem.mapper")
public class BootEat implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(BootEat.class,args);
    }
    @Value("${spring.servlet.multipart.location}")
    String upload;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///"+upload);
    }
}

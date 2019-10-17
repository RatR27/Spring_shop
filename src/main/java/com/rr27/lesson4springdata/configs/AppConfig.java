package com.rr27.lesson4springdata.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("com.rr27.lesson4springdata")
public class AppConfig {

}

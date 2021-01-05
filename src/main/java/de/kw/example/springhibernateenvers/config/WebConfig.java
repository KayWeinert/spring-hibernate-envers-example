package de.kw.example.springhibernateenvers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("de.kw.example.springhibernateenvers")
public class WebConfig {

}

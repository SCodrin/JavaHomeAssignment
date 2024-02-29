package com.example.interviewskeleton;

import com.example.interviewskeleton.config.GreetingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GreetingProperties.class)
public class InterviewSkeletonApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewSkeletonApplication.class, args);
    }

}
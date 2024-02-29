package com.example.interviewskeleton.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "greeting")
@Getter
@Setter
public class GreetingProperties {
		
		private String defaultLocale = "en";
		private Map<String, String> morning;
		private Map<String, String> afternoon;
		private Map<String, String> evening;
		
}
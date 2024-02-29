package com.example.interviewskeleton.service;

import com.example.interviewskeleton.config.GreetingProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class GreetingService {
		
		private final GreetingProperties greetingProperties;
		
		public String getGreeting(String name, String locale) {
				log.info("Getting greeting for: {} with locale: {}", name, locale);
				
				LocalTime now = LocalTime.now();
				Map<String, String> greetingsMap = getGreetingsForTimeOfDay(now);
				
				return greetingsMap.getOrDefault(locale, "Hello, {name}!").replace("{name}", name);
		}
		
		private Map<String, String> getGreetingsForTimeOfDay(LocalTime now) {
				if (now.isBefore(LocalTime.of(12, 0))) {
						return greetingProperties.getMorning();
				} else if (now.isBefore(LocalTime.of(18, 0))) {
						return greetingProperties.getAfternoon();
				} else {
						return greetingProperties.getEvening();
				}
		}
}
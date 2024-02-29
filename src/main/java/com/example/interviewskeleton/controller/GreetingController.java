package com.example.interviewskeleton.controller;

import com.example.interviewskeleton.service.GreetingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/greeting")
@AllArgsConstructor
@Slf4j
public class GreetingController {
		
		private final GreetingService greetingService;
		
		@GetMapping("/greet/{name}")
    public String greet(@PathVariable String name,
												@RequestParam(defaultValue = "en", name = "locale") String locale) {
        return greetingService.getGreeting(name, locale);
    }
}
package com.example.interviewskeleton.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@Slf4j
public class FirstProblemService {
		private static final String FORBIDDEN_CHARACTER = "a";

		public void saveWords(List<String> words) {
				validateWords(words);
				performAsyncOperations(words);
		}
		
		private void performAsyncOperations(List<String> words) {
				
				Flux.fromIterable(words)
				.parallel()
				.runOn(Schedulers.parallel())
				.flatMap(word -> Mono.fromRunnable(() -> saveWordToExternalApi(word))
                             .onErrorResume(e -> {
                                 log.error("Failed to save word: {}", word, e);
                                 return Mono.empty(); // Proceed without interrupting the whole flow
                             }))
				.sequential()
				.doOnError(e -> log.error("An error occurred during the saving process", e))
				.subscribe();
		}
		
		private void validateWords(List<String> words) {
				for (String word : words) {
						if (word == null || word.contains(FORBIDDEN_CHARACTER)) {
								log.warn("This word is not valid: {}", word);
								throw new UnsupportedOperationException("This word is not valid: " + word);
						}
				}
		}
		
		private void saveWordToExternalApi(String word) {
				try {
						// Here we call external API. We use a sleep of 1s to simulate that it takes a lot of time, and we have no control over it.
						Thread.sleep(1000);
				} catch (Throwable anyException) {
						log.info("[BEST EFFORT] Saving word '{}' failed: {}", word, anyException.getMessage());
				}
		}
}
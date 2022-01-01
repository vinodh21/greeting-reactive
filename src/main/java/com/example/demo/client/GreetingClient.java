package com.example.demo.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.pojo.Greeting;

import reactor.core.publisher.Mono;

@Component
public class GreetingClient {

	private final WebClient client;

	public GreetingClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:8081").build();
	}

	public Mono<String> getMessage() {
		return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Greeting.class)
				.map(Greeting::getMessage);
	}
}

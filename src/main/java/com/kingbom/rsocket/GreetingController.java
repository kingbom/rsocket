package com.kingbom.rsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Controller
public class GreetingController {

    @MessageMapping("greetings")
    public Flux<GreetingResponse> greet(GreetingRequest request) {
        var stream = Stream.generate(() -> new GreetingResponse(String.format("Hi %s  @%s", request.getName(), Instant.now())));
        return Flux.fromStream(stream).delayElements(Duration.ofSeconds(1));
    }
}

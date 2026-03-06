package com.adhnajwa99.ai;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class ChatController {

    @PostMapping("/ask")
    public Map<String, String> askAI(@RequestBody Map<String, String> request) {
        String userMsg = request.get("question");
        String aiAnswer = "Hello! You asked: " + userMsg;
        return Map.of("answer", aiAnswer);
    }
}

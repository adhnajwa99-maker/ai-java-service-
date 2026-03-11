package com.najwa.aijavaservice.controller;

import org.springframework.web.bind.annotation.*;
import com.najwa.aijavaservice.service.AiService;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

}
@PostMapping("/generate")
public String generateCode(@RequestBody String prompt, @RequestParam(defaultValue="Java") String language) {
    return aiService.generateCode(prompt, language);
}
@PostMapping("/analyze")
public String analyzeCode(@RequestBody String code) {
    return aiService.analyzeCode(code);
}
@PostMapping("/explain")
public String explainCode(@RequestBody String code) {
    return aiService.explainCode(code);
}

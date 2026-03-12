package com.najwa.aijavaservice.controller;

import com.najwa.aijavaservice.dto.CodeRequest;
import com.najwa.aijavaservice.dto.CustomResponse;
import com.najwa.aijavaservice.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/explain")
    public CustomResponse explainCode(@RequestBody CodeRequest request) {
        String explanation = aiService.explainCode(request.getCode());
        return CustomResponse.ok(explanation);
    }

    @PostMapping("/generate")
    public CustomResponse generateCode(@RequestBody CodeRequest request) {
        String code = aiService.generateCode(request.getCode());
        return CustomResponse.ok(code);
    }
}

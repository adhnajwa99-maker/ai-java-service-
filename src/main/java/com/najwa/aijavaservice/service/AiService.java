package com.najwa.aijavaservice.service;

import org.springframework.stereotype.Service;

@Service
public class AiService {

    public String explainCode(String code) {
        if(code.contains("for")) return "This code uses a for loop.";
        if(code.contains("if")) return "This code uses an if statement.";
        return "Code explanation (AI placeholder).";
    }

    public String generateCode(String prompt) {
        return "// Generated code based on prompt: " + prompt;
    }
}

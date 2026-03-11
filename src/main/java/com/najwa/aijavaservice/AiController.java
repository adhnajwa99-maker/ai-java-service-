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
package com.najwa.aijavaservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.najwa.aijavaservice.service.AiService;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private static final Logger logger = LoggerFactory.getLogger(AiController.class);

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateCode(
            @RequestBody String prompt,
            @RequestParam(defaultValue = "Java") String language) {

        // Validate input
        if (prompt == null || prompt.trim().isEmpty()) {
            logger.warn("Empty prompt received");
            return ResponseEntity.badRequest().body("Prompt cannot be empty");
        }

        try {
            // Log the request
            logger.info("Generating {} code for prompt: {}", language, prompt);
            String result = aiService.generateCode(prompt, language);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Log the error
            logger.error("Error generating code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating code: " + e.getMessage());
        }
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeCode(@RequestBody String code) {
        // Validate input
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Code cannot be empty");
        }
        try {
            // Log the request
            logger.info("Analyzing code...");
            String result = aiService.analyzeCode(code);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Log the error
            logger.error("Error analyzing code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error analyzing code: " + e.getMessage());
        }
    }

    @PostMapping("/explain")
    public ResponseEntity<String> explainCode(@RequestBody String code) {
        // Validate input
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Code cannot be empty");
        }
        try {
            // Log the request
            logger.info("Explaining code...");
            String result = aiService.explainCode(code);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Log the error
            logger.error("Error explaining code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error explaining code: " + e.getMessage());
        }
    }
}
package com.najwa.aijavaservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.najwa.aijavaservice.service.AiService;

// Swagger imports (optional, if you add Swagger)
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/ai")
@Api(tags = "AI Java Service") // Swagger tag
public class AiController {

    private static final Logger logger = LoggerFactory.getLogger(AiController.class);

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    @ApiOperation(value = "Generate code snippet based on prompt", notes = "Provide prompt and language")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Code generated successfully"),
        @ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<CustomResponse> generateCode(
            @RequestParam(required = true) String prompt,
            @RequestParam(defaultValue = "Java") String language) {

        if (prompt == null || prompt.trim().isEmpty()) {
            logger.warn("Empty prompt received");
            return ResponseEntity.badRequest().body(
                    new CustomResponse("error", "Prompt cannot be empty", null));
        }

        try {
            logger.info("Generating {} code for prompt: {}", language, prompt);
            String result = aiService.generateCode(prompt, language);
            return ResponseEntity.ok(new CustomResponse("success", "Code generated", result));
        } catch (Exception e) {
            logger.error("Error generating code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("error", "Error generating code: " + e.getMessage(), null));
        }
    }

    @PostMapping("/analyze")
    @ApiOperation(value = "Analyze code", notes = "Provide code to analyze")
    public ResponseEntity<CustomResponse> analyzeCode(@RequestBody String code) {
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new CustomResponse("error", "Code cannot be empty", null));
        }
        try {
            logger.info("Analyzing code...");
            String result = aiService.analyzeCode(code);
            return ResponseEntity.ok(new CustomResponse("success", "Analysis complete", result));
        } catch (Exception e) {
            logger.error("Error analyzing code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("error", "Error analyzing code: " + e.getMessage(), null));
        }
    }

    @PostMapping("/explain")
    @ApiOperation(value = "Explain code", notes = "Provide code to get explanation")
    public ResponseEntity<CustomResponse> explainCode(@RequestBody String code) {
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new CustomResponse("error", "Code cannot be empty", null));
        }
        try {
            logger.info("Explaining code...");
            String result = aiService.explainCode(code);
            return ResponseEntity.ok(new CustomResponse("success", "Explanation generated", result));
        } catch (Exception e) {
            logger.error("Error explaining code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("error", "Error explaining code: " + e.getMessage(), null));
        }
    }

    // Custom response class
    public static class CustomResponse {
        private String status;
        private String message;
        private String data;

        public CustomResponse(String status, String message, String data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public String getStatus() { return status; }
        public String getMessage() { return message; }
        public String getData() { return data; }

        public void setStatus(String status) { this.status = status; }
        public void setMessage(String message) { this.message = message; }
        public void setData(String data) { this.data = data; }
    }
                }
package com.najwa.aijavaservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.najwa.aijavaservice.service.AiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/ai")
@Api(tags = "AI Java Service")
public class AiController {

    private static final Logger logger = LoggerFactory.getLogger(AiController.class);

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    // ------------------------------
    // Generate Code Endpoint
    // ------------------------------
    @PostMapping("/generate")
    @ApiOperation(value = "Generate code snippet based on JSON request")
    public ResponseEntity<CustomResponse> generateCode(@RequestBody GenerateCodeRequest request) {

        if (request.getPrompt() == null || request.getPrompt().trim().isEmpty()) {
            logger.warn("Empty prompt received");
            return ResponseEntity.badRequest().body(
                    new CustomResponse("error", "Prompt cannot be empty", null));
        }

        try {
            logger.info("Generating {} code for prompt: {}", request.getLanguage(), request.getPrompt());
            String result = aiService.generateCode(request.getPrompt(), request.getLanguage());
            return ResponseEntity.ok(new CustomResponse("success", "Code generated", result));
        } catch (Exception e) {
            logger.error("Error generating code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("error", "Error generating code: " + e.getMessage(), null));
        }
    }

    // ------------------------------
    // Analyze Code Endpoint
    // ------------------------------
    @PostMapping("/analyze")
    @ApiOperation(value = "Analyze code based on JSON request")
    public ResponseEntity<CustomResponse> analyzeCode(@RequestBody AnalyzeCodeRequest request) {
        if (request.getCode() == null || request.getCode().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new CustomResponse("error", "Code cannot be empty", null));
        }
        try {
            logger.info("Analyzing code...");
            String result = aiService.analyzeCode(request.getCode());
            return ResponseEntity.ok(new CustomResponse("success", "Analysis complete", result));
        } catch (Exception e) {
            logger.error("Error analyzing code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("error", "Error analyzing code: " + e.getMessage(), null));
        }
    }

    // ------------------------------
    // Explain Code Endpoint
    // ------------------------------
    @PostMapping("/explain")
    @ApiOperation(value = "Explain code based on JSON request")
    public ResponseEntity<CustomResponse> explainCode(@RequestBody ExplainCodeRequest request) {
        if (request.getCode() == null || request.getCode().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new CustomResponse("error", "Code cannot be empty", null));
        }
        try {
            logger.info("Explaining code...");
            String result = aiService.explainCode(request.getCode());
            return ResponseEntity.ok(new CustomResponse("success", "Explanation generated", result));
        } catch (Exception e) {
            logger.error("Error explaining code", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse("error", "Error explaining code: " + e.getMessage(), null));
        }
    }

    // ------------------------------
    // JSON Request Objects
    // ------------------------------
    public static class GenerateCodeRequest {
        private String prompt;
        private String language = "Java"; // default

        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }

        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
    }

    public static class AnalyzeCodeRequest {
        private String code;
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    public static class ExplainCodeRequest {
        private String code;
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    // ------------------------------
    // Custom Response Object
    // ------------------------------
    public static class CustomResponse {
        private String status;
        private String message;
        private String data;

        public CustomResponse(String status, String message, String data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public String getStatus() { return status; }
        public String getMessage() { return message; }
        public String getData() { return data; }

        public void setStatus(String status) { this.status = status; }
        public void setMessage(String message) { this.message = message; }
        public void setData(String data) { this.data = data; }
    }
}
POST /api/ai/generate
Content-Type: application/json

{
  "prompt": "Create a function to reverse a string",
  "language": "Java"
}
POST /api/ai/analyze
Content-Type: application/json

{
  "code": "public String reverse(String s) { return new StringBuilder(s).reverse().toString(); }"
}
POST /api/ai/explain
Content-Type: application/json

{
  "code": "public String reverse(String s) { return new StringBuilder(s).reverse().toString(); }"
}

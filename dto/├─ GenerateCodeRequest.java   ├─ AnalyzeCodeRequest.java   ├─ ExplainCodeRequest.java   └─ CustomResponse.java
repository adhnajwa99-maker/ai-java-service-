package com.najwa.aijavaservice.dto;

import javax.validation.constraints.NotBlank;

public class GenerateCodeRequest {

    @NotBlank(message = "Prompt must not be blank")
    private String prompt;

    private String language = "Java";

    // Getters & Setters
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
package com.najwa.aijavaservice.dto;

import javax.validation.constraints.NotBlank;

public class AnalyzeCodeRequest {

    @NotBlank(message = "Code must not be blank")
    private String code;

    // Getters & Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
package com.najwa.aijavaservice.dto;

import javax.validation.constraints.NotBlank;

public class ExplainCodeRequest {

    @NotBlank(message = "Code must not be blank")
    private String code;

    // Getters & Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
package com.najwa.aijavaservice.dto;

public class CustomResponse {

    private String status;
    private String message;
    private String data;

    public CustomResponse(String status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters & Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}

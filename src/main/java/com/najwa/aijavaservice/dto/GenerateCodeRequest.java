package com.najwa.aijavaservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateCodeRequest {

    @NotBlank(message = "Prompt must not be blank")
    @Size(min = 3, max = 100, message = "Prompt length must be between 3 and 100")
    @JsonProperty("prompt")
    private String prompt;

    @Pattern(regexp = "^(Java|Python|JavaScript)$", message = "Language must be Java, Python, or JavaScript")
    @JsonProperty("language")
    private String language = "Java"; // default language

    public GenerateCodeRequest() {}

    public GenerateCodeRequest(String prompt, String language) {
        this.prompt = prompt;
        this.language = language;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

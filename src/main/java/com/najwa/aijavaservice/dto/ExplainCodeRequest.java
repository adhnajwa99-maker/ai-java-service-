package com.najwa.aijavaservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExplainCodeRequest {

    @NotBlank(message = "Code must not be blank")
    @Size(min = 3, message = "Code length must be at least 3 characters")
    @Pattern(
        regexp = "^[a-zA-Z0-9\\s\\{\\}\\(\\)\\;\\:\\=\\+\\-\\_\\/\\%\\!\\?\\,\\.\\[\\]]+$",
        message = "Code contains invalid characters"
    )
    @JsonProperty("code")
    private String code;

    @JsonProperty("language")
    private String language = "Java"; // default language

    public ExplainCodeRequest() {}

    public ExplainCodeRequest(String code, String language) {
        this.code = code;
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

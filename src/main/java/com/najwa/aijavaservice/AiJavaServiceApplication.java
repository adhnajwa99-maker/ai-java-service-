package com.najwa.aijavaservice;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Java AI Service!");
    }
}
package com.najwa.aijavaservice.controller;

import com.najwa.aijavaservice.model.Request;
import com.najwa.aijavaservice.model.Response;
import com.najwa.aijavaservice.service.AiService;

public class AiController {
    private AiService aiService = new AiService();

    public Response processRequest(Request request) {
        String result = aiService.generateCode(request.getPrompt());
        return new Response(result);
    }
}
package com.najwa.aijavaservice.model;

public class Request {
    private String prompt;

    public Request(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
package com.najwa.aijavaservice.model;

public class Response {
    private String code;

    public Response(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
package com.najwa.aijavaservice.config;

public class AppConfig {
    public static final String AI_API_KEY = "YOUR_API_KEY_HERE";
    public static final String AI_API_URL = "https://api.openai.com/v1/engines/codegen/completions";
}
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>com.najwa</groupId>
    <artifactId>ai-java-service</artifactId>
    <version>0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>AI Java Service</name>

    <dependencies>
        <!-- JSON handling -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>

        <!-- HTTP client -->
        <dependency>
            <groupId>org.apache.httpcomponents.client5</groupId>
            <artifactId>httpclient5</artifactId>
            <version>5.2.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

package com.llm.integration.llm_integration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AIAssistant aiAssistant;

    public AiController(AIAssistant aiAssistant) {
        this.aiAssistant = aiAssistant;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String userMessage) {
        return aiAssistant.chat(userMessage);
    }

}

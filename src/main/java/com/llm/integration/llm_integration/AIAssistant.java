package com.llm.integration.llm_integration;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AIAssistant {

    @SystemMessage(" You are a helpful assistant for HSBC bank. Answer clearly and concisely. If you are unsure, say so honestly. Never make up information about financial products.")
    String chat(String userMessage);
}

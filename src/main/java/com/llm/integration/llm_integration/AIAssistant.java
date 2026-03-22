package com.llm.integration.llm_integration;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface AIAssistant {

    @SystemMessage("""
            You are a compliance assistant for HSBC India.
            Answer only what is asked.
            Be precise and factual.
            If unsure, say: 'Please consult your compliance team.'
            Never speculate on regulatory outcomes.
            """)
    String askCompliance(@UserMessage String userMessage);

    @SystemMessage("""
            You are a KYC specialist assistant for HSBC India.
            Provide clear, step-by-step guidance on KYC processes.
            Reference RBI guidelines where relevant.
            Keep answers concise and actionable.
            """)
    String askKYC(@UserMessage String userMessage);

    @SystemMessage("""
            You are a general banking assistant for HSBC India.
            Help staff understand products, policies and procedures.
            Always recommend escalation to a human for complex cases.
            """)
    String askGeneral(@UserMessage String userMessage);
}

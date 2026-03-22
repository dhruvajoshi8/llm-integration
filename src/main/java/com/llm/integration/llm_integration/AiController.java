package com.llm.integration.llm_integration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llm.integration.llm_integration.beans.AIRequest;
import com.llm.integration.llm_integration.beans.AIResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/ai")
@Slf4j
public class AiController {

    private final AIAssistant aiAssistant;

    public AiController(AIAssistant aiAssistant) {
        this.aiAssistant = aiAssistant;
    }

    @PostMapping("/ask")
    public ResponseEntity<AIResponse> ask(@RequestBody AIRequest request) {

        long start = System.currentTimeMillis();

        try {
            String answer = route(request);
            long latency = System.currentTimeMillis() - start;
            log.info("Response time: {} ms", latency);

            return ResponseEntity.ok(AIResponse.builder()
                    .answer(answer)
                    .category(request.getCategory())
                    .responseTimeinMs(latency)
                    .success(true)
                    .build());

        } catch (Exception e) {
            log.error("Error processing request", e);
            return ResponseEntity.internalServerError().body(AIResponse.builder()
                    .answer("An error occurred while processing your request.")
                    .category(request.getCategory())
                    .responseTimeinMs(System.currentTimeMillis() - start)
                    .success(false)
                    .build());
        }

    }

    private String route(AIRequest request) {
        return switch (request.getCategory().toLowerCase()) {
            case "compliance" -> aiAssistant.askCompliance(request.getQuery());
            case "kyc" -> aiAssistant.askKYC(request.getQuery());
            case "general" -> aiAssistant.askGeneral(request.getQuery());
            default -> throw new UnsupportedOperationException("Unimplemented method 'route'");
        };
    }

}

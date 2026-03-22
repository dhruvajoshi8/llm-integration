package com.llm.integration.llm_integration.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AIResponse {

    private String answer;

    private String category;

    private long responseTimeinMs;

    private boolean success;
}

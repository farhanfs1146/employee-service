package com.example.employee_service.config;

import com.example.employee_service.exception.ResourceNotFoundException;
import com.example.employee_service.exception.ValidationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    public static class FeignErrorDecoder implements ErrorDecoder {
        private final ErrorDecoder defaultErrorDecoder = new Default();
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Exception decode(String methodKey, Response response) {
            log.error("Feign error on method: {} with status: {}", methodKey, response.status());

            String errorMessage = extractErrorMessage(response);

            // Preserve the original error message from master-data-service
            return switch (response.status()) {
                case 400 -> new ValidationException(errorMessage);
                case 404 -> new ResourceNotFoundException(errorMessage);
                default -> defaultErrorDecoder.decode(methodKey, response);
            };
        }

        private String extractErrorMessage(Response response) {
            try {
                if (response.body() != null) {
                    InputStream inputStream = response.body().asInputStream();
                    String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                    // Try to parse the ApiResponse format to extract the message
                    JsonNode jsonNode = objectMapper.readTree(body);
                    if (jsonNode.has("message")) {
                        return jsonNode.get("message").asText();
                    }
                    return body;
                }
            } catch (IOException e) {
                log.error("Error reading response body", e);
            }
            return "Error communicating with master-data-service";
        }
    }
}

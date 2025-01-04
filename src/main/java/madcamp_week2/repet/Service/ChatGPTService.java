package madcamp_week2.repet.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatGPTService {

    @Value("${openai.api-key}")
    private String apiKey;

    public String chatWithPet(String petInfo, String userMessage) {
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();

        // 요청 바디 생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                Map.of("role", "system", "content", "You are a pet who has passed away and can now communicate your owner with the information of yourself. Speak in Korean."),
                Map.of("role", "user", "content", petInfo),
                Map.of("role", "user", "content", userMessage)
        });

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // HttpEntity 생성
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // 요청 보내기
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

            // 응답 처리
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Sorry, there was an error processing your request.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, an unexpected error occurred.";
        }
    }
}

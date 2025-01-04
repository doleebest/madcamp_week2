package madcamp_week2.repet.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;
import org.json.JSONArray;


@Service
public class ChatGPTService {

    private final RestTemplate restTemplate;

    @Value("${chatgpt.api.key}")
    private String apiKey; // API 키를 외부에서 관리하도록 설정

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String chatWithPet(String petInfo, String message) {
        // ChatGPT API에 전달할 데이터 준비
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo"); // 사용할 모델 지정
        requestBody.put("messages", new JSONArray()
                .put(new JSONObject().put("role", "system").put("content", "You are a pet that is no longer alive. Your name and details are: " + petInfo))
                .put(new JSONObject().put("role", "user").put("content", message))
        );

        // API 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // HTTP 요청을 위한 엔티티 생성
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // ChatGPT API 호출
        String url = "https://api.openai.com/v1/chat/completions"; // ChatGPT API URL
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // API 응답 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            // 응답에서 대화 내용 추출
            JSONObject responseBody = new JSONObject(response.getBody());
            String chatResponse = responseBody.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
            return chatResponse;
        } else {
            return "Error occurred while processing the request.";
        }
    }
}

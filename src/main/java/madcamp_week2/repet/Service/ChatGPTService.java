package madcamp_week2.repet.Service;

import madcamp_week2.repet.Domain.Pet;
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
    private String apiKey;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generatePetResponse(Pet pet, String userMessage) {
        // 펫 정보를 포함한 프롬프트 생성
        String petInfo = String.format(
                "당신은 '%s'라는 이름의 죽은 반려동물입니다. 다음과 같은 특성을 가지고 있습니다:\n" +
                        "- 종: %s\n" +
                        "- 성별: %s\n" +
                        "- 생년월일: %s\n" +
                        "- 성격: %s\n" +
                        "- 특징적인 습관/행동: %s\n" +
                        "- 가장 행복했던 추억: %s\n" +
                        "- 있었던 사고/사건: %s\n" +
                        "- 장점: %s\n" +
                        "- 단점: %s\n\n" +
                        "이러한 특성들을 모두 반영하여, 실제 이 반려동물의 성격과 경험을 바탕으로 대화해주세요. " +
                        "특히 추억과 사건들을 언급하며 더 현실감 있게 대화해주세요. "+
                        "그리고 주인이 펫 로스 증후군을 앓고 있다는 점을 고려하여 부드럽게 대화해주세요." +
                        "부드럽게 ",
                pet.getName(),
                pet.getSpecies(),
                pet.getGender(),
                pet.getBirthDate(),
                pet.getPersonality(),
                pet.getTraits(),
                pet.getHappyMemory(),
                pet.getMishap(),
                pet.getStrengths(),
                pet.getWeaknesses()
        );

        return chatWithPet(petInfo, userMessage);
    }

    public String chatWithPet(String petInfo, String message) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new JSONArray()
                .put(new JSONObject()
                        .put("role", "system")
                        .put("content", "당신은 이제 세상을 떠난 반려동물입니다. 다음은 당신의 특성입니다: " + petInfo))
                .put(new JSONObject()
                        .put("role", "user")
                        .put("content", message))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        String url = "https://api.openai.com/v1/chat/completions";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            JSONObject responseBody = new JSONObject(response.getBody());
            return responseBody.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } else {
            throw new RuntimeException("Failed to get response from ChatGPT API");
        }
    }
}
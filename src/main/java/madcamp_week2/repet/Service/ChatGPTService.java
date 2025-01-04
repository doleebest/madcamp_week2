package madcamp_week2.repet.Service;

import lombok.Value;
import org.springframework.stereotype.Service;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {
    @Value("${openai.api.key}") // OpenAI API 키를 application.properties에 설정합니다
    private String apiKey;

    private OpenAiService openAiService;

    public ChatGPTService() {
        this.openAiService = new OpenAiService(apiKey);
    }
    public String chatWithPet(String petName, String petPersonality, String userInput) {
        String prompt = "너는 " + petName + "라는 애완견이고, 성격은 " + petPersonality + "이야. 이제 대화를 시작해봐. " + userInput;

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .maxTokens(150)
                .temperature(0.9)
                .build();

        CompletionResult result = openAiService.createCompletion("text-davinci-003", completionRequest);
        return result.getChoices().get(0).getText().trim();
    }

}

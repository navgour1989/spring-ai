package com.nav.ai.spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GeminiModelController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat-with-gemini")
    public String chatWithGemini(@RequestParam("msg") String msg) {
       // return chatClient.prompt().user(msg).call().content();
        return chatClient.prompt(msg)
                .call()
                .content();
    }

    @PostMapping("/from-files")
    public ResponseEntity<String> analyzeFromMultipart(@RequestParam("file") MultipartFile file, @RequestParam("prompt") String prompt) {
        Media media =  new Media(MimeTypeUtils.IMAGE_PNG, file.getResource());

        String response = chatClient.prompt()
                //.system(SYSTEM_PROMPT_TEMPLATE)
                .user(userSpec -> userSpec
                .text(prompt)
                .media(media))
                .call()
                .content();

        return ResponseEntity.ok(response);
    }

    /*private final RestClient restClient;

    public GeminiModelController(RestClient.Builder builder) {
        log.info("GeminiModelController...");
        this.restClient = builder
                .baseUrl(GEMINI_API_URL)
                .build();
    }

    private static final Logger log = LoggerFactory.getLogger(GeminiModelController.class);
   @Value("${spring.ai.openai.api-key}")
    private String GEMINI_API_KEY;

    @Value("${spring.ai.openai.base-url}")
    private String GEMINI_API_URL;

    *//*
        curl https://generativelanguage.googleapis.com/v1beta/openai/models \                                                                                                                                                                                                  ✔  10s   base 
        -H "Authorization: Bearer GEMINI_API_KEY"
     *//*
    @GetMapping("/models")
    public List<GeminiModel> models() {
        ResponseEntity<ModelListResponse> response = restClient.get()
                .uri("https://generativelanguage.googleapis.com/v1beta/openai/models")
                .header("Authorization","Bearer " + GEMINI_API_KEY)
                .retrieve()
                .toEntity(ModelListResponse.class);
        return response.getBody().data();
    }*/

}
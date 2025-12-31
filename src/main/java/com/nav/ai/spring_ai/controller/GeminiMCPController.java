package com.nav.ai.spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeminiMCPController {

    private final ChatClient chatClient;

    public GeminiMCPController(ChatClient.Builder chatClientBuilder,
                             ToolCallbackProvider mcpToolProvider) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(mcpToolProvider)
                .build();
    }

    @GetMapping("/chat-gemini-mcp")
    public String chatWithGemini(@RequestParam("msg") String msg) {
        return chatClient.prompt(msg)
                .system("You are a helpful assistant. Use the provided tools for weather and products. For all other topics, use your internal knowledge or the Google Search tool.")
                .call()
                .content();
    }

    /*@PostMapping("/from-files")
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
    }*/

}
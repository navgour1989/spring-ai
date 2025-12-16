package com.nav.ai.spring_ai.controller;

import com.nav.ai.spring_ai.service.GroqChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GroqChatController {
    @Autowired
    private GroqChatService chatService;

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat-with-open-ai-groq")
    public String chat(@RequestParam("msg") String msg) {
        //return chatService.chat(msg);
        return chatClient.prompt().user(msg).call().content();
    }

    @PostMapping("/chat-with-image")
    public String chat(@RequestPart("prompt") String prompt, @RequestPart("file") MultipartFile file) {
        return chatClient.prompt()
                .user(userSpec -> userSpec
                .text(prompt)
                .media(MimeTypeUtils.IMAGE_PNG, file.getResource()))
                .call()
                .content();
    }
}

package com.nav.ai.spring_ai.service;

import java.util.List;

public record ModelListResponse(String object, List<GeminiModel> data) {
}
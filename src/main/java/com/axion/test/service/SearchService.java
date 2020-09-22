package com.axion.test.service;

import com.axion.test.model.client.HandsetClientResponseDto;
import reactor.core.publisher.Flux;

public interface SearchService {
    public Flux<HandsetClientResponseDto> searchHandsetByKeywords(String ... keywords);
}

package com.axion.test.client;

import com.axion.test.enums.ErrorCodes;
import com.axion.test.exception.HandsetServiceUnavailableException;
import com.axion.test.model.client.HandsetClientResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@Slf4j
public class HandsetServiceClient {

    public Flux<HandsetClientResponseDto> list(String baseUrl, String endpoint) {
        log.info("Fetching all handsets:");
        return webClient(baseUrl).get()
                .uri(endpoint)
                .accept(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    //send this log to splunk ELASTIC STACK , in there you can raise an alert
                    log.error("Error in Fetching handset list:" + clientResponse.rawStatusCode());
                    return Mono.error(new HandsetServiceUnavailableException(ErrorCodes.HANDSET_SERVICE_DOWN.getMessage(), ErrorCodes.HANDSET_SERVICE_DOWN));
                })
                .bodyToMono(String.class) //returned string is json response
                .flatMapMany(this::mapJsonStringToList);

    }

    @SneakyThrows
    public Flux<HandsetClientResponseDto> mapJsonStringToList(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        List<HandsetClientResponseDto> details = mapper.readValue(jsonString, new TypeReference<List<HandsetClientResponseDto>>() {
        });
        return Flux.fromIterable(details);
    }

    public WebClient webClient(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }


}

package com.axion.test.service;


import com.axion.test.client.HandsetServiceClient;
import com.axion.test.model.client.HandsetClientResponseDto;
import com.axion.test.service.impl.SearchServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@Slf4j
class SearchServiceTest {
    private HandsetServiceClient handsetServiceClientMock;
    private SearchService searchService;
    private final String filePath = "./src/test/resources/data.json";
    private  Flux<HandsetClientResponseDto> listHandsets = null;
    private String keywords[]= new String[4];

    @BeforeEach
    public void init() {
        handsetServiceClientMock = Mockito.mock(HandsetServiceClient.class);
        searchService = new SearchServiceImpl(handsetServiceClientMock, "", "");
        //Load String of json file
        String jsonString = loadJsonFile(filePath);
        when(handsetServiceClientMock.mapJsonStringToList(jsonString)).thenCallRealMethod();  // Real implementation
        listHandsets = handsetServiceClientMock.mapJsonStringToList(jsonString);
    }

    @Test
    public void searchHandsetByKeywords_filterByPrice_theReturnFilteredHandsets() throws InterruptedException {
        //priceEur, sim, announceDate, price
        keywords[0] = "200"; //priceEuro
        keywords[1] = null   ; // sim
        keywords[2] = null;  //announceDate
        keywords [3] = null; // price

        when(handsetServiceClientMock.list(nullable(String.class), nullable(String.class))).thenReturn(listHandsets);
        Mockito.verify(handsetServiceClientMock, Mockito.times(0)).list(nullable(String.class), nullable(String.class));


       Flux<HandsetClientResponseDto> filteredHandsets = searchService.searchHandsetByKeywords(keywords);
        StepVerifier
                .create(filteredHandsets.log())
                .expectNextCount(10L)
                .verifyComplete();

    }

    @Test
    public void searchHandsetByKeywords_filterByAnnounceDateAndPrice_theReturnFilteredHandsets() throws InterruptedException {
        //priceEur, sim, announceDate, price
        keywords[0] = null; //priceEuro
        keywords[1] = null  ; // sim
        keywords[2] = "1999";  //announceDate
        keywords [3] = "200"; // price

        when(handsetServiceClientMock.list(nullable(String.class), nullable(String.class))).thenReturn(listHandsets);
        Mockito.verify(handsetServiceClientMock, Mockito.times(0)).list(nullable(String.class), nullable(String.class));


        Flux<HandsetClientResponseDto> filteredHandsets = searchService.searchHandsetByKeywords(keywords);

        StepVerifier
                .create(filteredHandsets.log())
                .expectNextCount(2)
                .verifyComplete();

    }


    @Test
    public void searchHandsetByKeywords_filterBySim_theReturnFilteredHandsets() throws InterruptedException {
        //priceEur, sim, announceDate, price
        keywords[0] = null; //priceEuro
        keywords[1] = "eSim"  ; // sim
        keywords[2] = null;  //announceDate
        keywords [3] = null; // price

        when(handsetServiceClientMock.list(nullable(String.class), nullable(String.class))).thenReturn(listHandsets);
        Mockito.verify(handsetServiceClientMock, Mockito.times(0)).list(nullable(String.class), nullable(String.class));


        Flux<HandsetClientResponseDto> filteredHandsets = searchService.searchHandsetByKeywords(keywords);

        StepVerifier
                .create(filteredHandsets.log())
                .expectNextCount(18)
                .verifyComplete();

    }

    @SneakyThrows
    private String loadJsonFile(String filename) {
        return Files.lines(Paths.get(filename))
                .parallel()
                .collect(Collectors.joining());
    }

}
package com.axion.test.service.impl;

import com.axion.test.client.HandsetServiceClient;
import com.axion.test.model.client.HandsetClientResponseDto;
import com.axion.test.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Predicate;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    private HandsetServiceClient handsetServiceClient;

    private String baseUrl;
    private String endpointSearchList;

    public SearchServiceImpl(HandsetServiceClient handsetServiceClient, @Value("${handset.service.baseUrl}") String baseUrl,
                             @Value("${handset.service.endpoints.search.list}") String endpointSearchList) {
        this.handsetServiceClient = handsetServiceClient;
        this.endpointSearchList = endpointSearchList;
        this.baseUrl = baseUrl;
    }

    @Override
    public Flux<HandsetClientResponseDto> searchHandsetByKeywords(String... keywords) {
        return filterHandsetsByKeywords(handsetServiceClient.list(baseUrl, endpointSearchList), keywords);
    }

    private Flux<HandsetClientResponseDto> filterHandsetsByKeywords(Flux<HandsetClientResponseDto> listHandsets, String... keyword) {

        //the price can have different search name (price, priceEuro)
        String price = keyword[0] != null ? keyword[0] : keyword[3];
        return listHandsets.filter(filterByPriceEur(price))
               .filter(filterBySim(keyword[1]))
                .filter(filterByAnnounceDate(keyword[2]));
    }

    private  Predicate<HandsetClientResponseDto> filterByPriceEur(String priceEur) {
        //This if will be executed once, no performance issue
        if(priceEur ==null)
            return p -> true;
        return p -> p.getRelease().getPriceEur().equals(priceEur);
    }

    private  Predicate<HandsetClientResponseDto> filterBySim(String sim) {
        //This if will be executed once, no performance issue
        if(sim ==null || sim.isEmpty())
            return p -> true;
        log.info("sim:{}", sim);
        return p -> p.getSim().toLowerCase().contains(sim.toLowerCase());
    }

    private  Predicate<HandsetClientResponseDto> filterByAnnounceDate(String announceDate) {
        if(announceDate ==null)
            return p -> true;
        return p -> p.getRelease().getAnnounceDate().equals(announceDate);
    }

}

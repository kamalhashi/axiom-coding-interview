package com.axion.test.controller;

import com.axion.test.mapper.SearchResponseDtoMapper;
import com.axion.test.model.dto.SearchResponseDto;
import com.axion.test.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class SearchHandsetController {
    @Autowired
    SearchService searchHandsetService;
    @Autowired
    SearchResponseDtoMapper searchResponseDtoMapper;


    @GetMapping("mobile/search")
    Mono<List<SearchResponseDto>> searchMobiles(
            @RequestParam(value = "sim", required = false) String sim,
            @RequestParam(value = "announceDate", required = false) String announceDate,
            @RequestParam(value = "priceEur",   required = false )  String priceEur,
            @RequestParam(value = "price",   required = false )  String price){
        return searchHandsetService.searchHandsetByKeywords(priceEur, sim, announceDate, price)
                .switchIfEmpty(Mono.error(new Exception("No handsets found")))
                .map( handset -> searchResponseDtoMapper.mapToDto(handset))
                .collectList();
               // .onErrorResume(HandsetServiceUnavailableException.class, e -> Mono.error(new HandsetServiceUnavailableException(ErrorCodes.HANDSET_SERVICE_DOWN.getMessage(), ErrorCodes.HANDSET_SERVICE_DOWN)));

    }

}

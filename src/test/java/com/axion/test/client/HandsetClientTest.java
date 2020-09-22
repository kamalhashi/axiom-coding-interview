package com.axion.test.client;

import com.axion.test.enums.ErrorCodes;
import com.axion.test.exception.HandsetServiceUnavailableException;
import com.axion.test.model.client.HandsetClientResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
class HandsetClientTest
{

    private final String baseUrl="https://a511e938-a640-4868-939e-6eef06127ca1.mock.pstmn.io/";
    private final String endPoint="handsets/list";

    private HandsetServiceClient handsetClient;

    @BeforeEach
    public void init() {

        log.info("startup");
        handsetClient = new HandsetServiceClient();
    }

    @Test
    public void findAll_whenServiceUP_thenReturnHandsets(){

       Flux<HandsetClientResponseDto> flux = handsetClient.list(baseUrl, endPoint);

       //Expected handset=105 handset ,
        StepVerifier
                .create(flux.log())
                .expectNextMatches( handset -> handset.getId() == 25846)
                .expectNextMatches( handset -> handset.getId() == 22895)
                .expectNextCount( 103L)
                .verifyComplete();

    }

    @Test
    public void findAll_whenServiceCannotReach_thenReturnError(){

        Flux<HandsetClientResponseDto> flux = handsetClient.list(baseUrl, "wrongUrl");

        //Expected handset=103 handset ,
        StepVerifier
                .create(flux.log())
                .expectErrorMatches(throwable -> throwable instanceof HandsetServiceUnavailableException &&
                        throwable.getMessage().equals(ErrorCodes.HANDSET_SERVICE_DOWN.getMessage())
                ).verify();
    }

}
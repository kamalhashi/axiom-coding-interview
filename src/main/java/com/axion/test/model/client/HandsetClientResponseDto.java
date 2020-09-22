package com.axion.test.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandsetClientResponseDto {
    private Long id;
    private String brand;
    private String phone;
    private String picture;
    private HandsetRelease release;
    private String sim;
    private String resolution;
    private HandsetHardware hardware;
}

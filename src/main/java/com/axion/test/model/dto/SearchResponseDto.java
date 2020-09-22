package com.axion.test.model.dto;

import com.axion.test.model.client.HandsetHardware;
import com.axion.test.model.client.HandsetRelease;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {
    private Long id;
    private String brand;
    private String phone;
    private String picture;
    private HandsetRelease release;
    private String sim;
    private String resolution;
    private HandsetHardware hardware;
}

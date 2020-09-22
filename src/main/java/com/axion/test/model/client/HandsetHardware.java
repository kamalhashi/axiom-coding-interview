package com.axion.test.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandsetHardware {
    private String audioJack;
    private String gps;
    private String battery;
}

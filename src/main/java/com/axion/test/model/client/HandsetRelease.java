package com.axion.test.model.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandsetRelease {
    //TODO change data type to formatted date
    private String announceDate;
    private String priceEur;
}

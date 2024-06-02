package com.graduation.PublicBicycle.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class XeRequest {

    @JsonProperty("imeixe")
    private String imeixe;
}

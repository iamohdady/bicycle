package com.graduation.PublicBicycle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResponse {
    @JsonProperty("accessToken")
    private String accessToken;
}

package com.graduation.PublicBicycle.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {
    @JsonProperty("username")
    public String username;

}

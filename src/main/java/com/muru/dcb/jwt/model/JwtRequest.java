package com.muru.dcb.jwt.model;

import lombok.Data;

@Data
public class JwtRequest {
    private final String userName;
    private final String password;
}

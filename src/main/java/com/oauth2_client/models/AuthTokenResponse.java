package com.oauth2_client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.time.Duration;
import java.util.Objects;

public class AuthTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_after")
    private Long expiresAfter;

    public AuthTokenResponse(OAuth2AccessToken token) {
        this.accessToken = token.getTokenValue();
        this.expiresAfter = Duration.between(
                Objects.requireNonNull(token.getIssuedAt()),
                Objects.requireNonNull(token.getExpiresAt())
        ).getSeconds();
    }
}

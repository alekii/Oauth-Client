package com.oauth2_client.api;


import com.oauth2_client.models.AuthTokenRequest;
import com.oauth2_client.models.AuthTokenResponse;
import com.oauth2_client.service.TokenGenerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/auth")
@RestController
public class AuthController {

    private final TokenGenerationService authService;

    public AuthController(TokenGenerationService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/token")
    public ResponseEntity<AuthTokenResponse> getAuthToken(@RequestBody AuthTokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.generateAuthToken(tokenRequest));
    }
}

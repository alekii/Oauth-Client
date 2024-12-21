package com.oauth2_client.api;


import com.oauth2_client.models.AuthTokenRequest;
import com.oauth2_client.models.AuthTokenResponse;
import com.oauth2_client.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/token", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AuthTokenResponse> getAuthToken(@RequestBody AuthTokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.generateAuthToken(tokenRequest));
    }
}

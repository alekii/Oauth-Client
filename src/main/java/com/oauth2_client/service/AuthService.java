package com.oauth2_client.service;

import com.oauth2_client.models.AuthTokenRequest;
import com.oauth2_client.models.AuthTokenResponse;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthTokenResponse generateAuthToken(AuthTokenRequest tokenRequest){
        ClientRegistration clientRegistration = ClientRegistration
                .withRegistrationId("dynamic")
                .clientId(tokenRequest.getClientId())
                .clientSecret(tokenRequest.getClientSecret())
                .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenUri("http://iam21.me.co.ke/realms/master/protocol/openid-connect/token")
                .build();

        InMemoryClientRegistrationRepository clientRegistrationRepository = new InMemoryClientRegistrationRepository(clientRegistration);

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository));

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("dynamic")
                .principal("dynamic")
                .build();

        var authorizedClient = authorizedClientManager.authorize(authorizeRequest);

        if (authorizedClient == null || authorizedClient.getAccessToken() == null) {
            throw new IllegalStateException("Failed to retrieve access token");
        }

        OAuth2AccessToken token = authorizedClient.getAccessToken();
        AuthTokenResponse tokenResponse = new AuthTokenResponse(token);

        return tokenResponse;
    }

}

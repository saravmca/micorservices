package com.microservices.oauth.springclientcredentialauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endPoints) throws Exception{
        endPoints.authenticationManager(authenticationManager);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)throws  Exception {
        clients.inMemory()
                .withClient("javainuse-client")
                .secret("javainuse-secret")
                .authorizedGrantTypes("client_credentials")
                .scopes("resource-server-read","resource-server-write");
    }
}

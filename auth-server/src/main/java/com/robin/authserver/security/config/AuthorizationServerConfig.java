package com.robin.authserver.security.config;

import com.robin.authserver.security.service.ClientService;
import com.robin.authserver.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 授权服务器核心配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 配置授权服务器的安全，意味着/oauth/token端点和/oauth/authorize端点都应该是安全的。
     * 默认的设置覆盖了绝大多数需求，所以一般情况下不需要做任何事情。
     * 如不配置security.allowFormAuthenticationForClients(); 无法通过/oauth/token访问客户端token
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
        .checkTokenAccess("permitAll()");
    }

    /**
     * 该处通过配置ClientDetailsService来配置注册到该授权服务器的客户端clients信息。
     * 注意，除非在下面的configure（AuthorizationServerEndpointsConfigurer）中指定
     * 了一个AuthenticationManager，否则密码授权模式不可用
     * 至少配置一个client，负责无法启动服务器
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientService);
    }

    /**
     * 该方法是用来配置授权服务器特性的（Authorization Server endpoints），主要是一些
     * 非安全的特性，比如token存储、token自定义、授权模式等。
     * 默认不需要任何配置，如果需要密码授权，则需要提供一个AuthenticationManager
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.tokenStore(jdbcTokenStore())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                .userDetailsService(userService);
    }

    @Bean
    public TokenStore jdbcTokenStore() {
        return new InMemoryTokenStore();
    }




}

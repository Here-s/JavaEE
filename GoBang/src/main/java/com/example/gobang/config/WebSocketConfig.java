package com.example.gobang.config;

import com.example.gobang.api.GameAPI;
import com.example.gobang.api.MatchAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private MatchAPI matchAPI;

    @Autowired
    private GameAPI gameAPI;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(matchAPI,"/findMatch")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
        registry.addHandler(gameAPI,"/game")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}

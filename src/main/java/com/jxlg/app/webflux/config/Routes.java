package com.jxlg.app.webflux.config;

import com.jxlg.app.webflux.service.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * 创建一个定义应用程序路由的路由类
 */
@Configuration
public class Routes {
    private UserHandler userHandler;

    public Routes(UserHandler userHandler){
        this.userHandler=userHandler;
    }

    /**
     * RouterFunction就像Spring Web中的@RequestMapping类一样。
     * 方法有如GET，POST，path，queryParam，accept，headers，contentType等
     */
    @Bean
    public RouterFunction<?> routerFunction(){
        return RouterFunctions.route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)),userHandler::handleGetUsers)
                .and(route(GET("/api/user/{userId}").and(accept(MediaType.APPLICATION_JSON)),userHandler::handleGetUserById));

    }
}

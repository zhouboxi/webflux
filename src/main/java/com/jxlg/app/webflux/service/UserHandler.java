package com.jxlg.app.webflux.service;

import com.jxlg.app.webflux.dao.UserRepository;
import com.jxlg.app.webflux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 创建带有处理 HTTP 请求函数的 Handler 类 相当于controller+service
 */
@Service
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    /**
     * ServerResponse.ok().body(UserRepository.getUsers(), User.class)
     * 可将此 Flux <User> 转换为 Mono<ServerResponse>
     */
    public Mono<ServerResponse> handleGetUsers(ServerRequest request){
        return ServerResponse.ok().body(userRepository.getUser(),User.class);
    }

    /**
     * ServerResponse.ok().body(Mono.just(user), User.class)
     * 将此 Mono<User> 转换为Mono<ServerResponse>
     *
     * 在给定的路径变量（pathVariable）中没有找到用户时，
     * ServerResponse.notFound().build() 返回一个 Mono<ServerResponse>，
     * 表名是一个返回 404 服务响应的流。
     */
    public Mono<ServerResponse> handleGetUserById(ServerRequest request){
        return userRepository.getUserById(request.pathVariable("userId"))
                .flatMap(user -> ServerResponse.ok().body(Mono.just(user),User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}

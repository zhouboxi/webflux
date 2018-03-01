package com.jxlg.app.webflux.dao;

import com.jxlg.app.webflux.entity.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
@Repository
public class UserRepository {
    //定义一个默认的参数-->实际从数据可读取
    private final List<User> userLists = Arrays.asList(new User(1L,"bobo"),new User(2L,"bill"));

    //定义一个方法
    //GetUserById() 返回一个 Mono<User> 对象，
    // 这个对象不论何时都会返回 0~1 个用户对象，
    // GetUsers() 返回一连串变动的用户对象，不论何时都包含 0~n 个用户对象。
    public Mono<User> getUserById(String userId){
        return Mono.justOrEmpty(userLists.stream().filter(user -> {
            return user.getId().equals(Long.valueOf(userId));
        }).findFirst().orElse(null));
    }

    public Flux<User> getUser(){
        return  Flux.fromIterable(userLists);
    }
}

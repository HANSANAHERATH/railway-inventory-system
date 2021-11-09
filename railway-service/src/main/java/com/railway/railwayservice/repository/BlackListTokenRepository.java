package com.railway.railwayservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@RequiredArgsConstructor
public class BlackListTokenRepository {
    private static final String REDIS_ENTITY = "token";

    private final RedisTemplate<String, String> redisTemplate;

    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public String findTokenByUserName(String username) {
        return hashOperations.get(REDIS_ENTITY, username);
    }

    public void saveToken(String username,String token) {
        hashOperations.put(REDIS_ENTITY, username, token);
    }
}

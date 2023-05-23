package com.rainyjune.board.redis.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public Boolean setValue(String key, Object value) {
        Boolean isSuccess = false;

        redisTemplate.opsForValue().set(key, value);
        isSuccess = true;

        return isSuccess;
    }

    public Boolean setValueWithTimeout(String key, Object value, Long timeOut) {
        Boolean isSuccess = false;

        redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.MINUTES);
        isSuccess = true;

        return isSuccess;
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean deleteValue(String key) {
        return redisTemplate.delete(key);
    }
}

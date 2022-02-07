package com.example.autocomplete.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class redisService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void insertToCache(String prefix, String value) {
        redisTemplate.boundSetOps(prefix).add(value);
    }

    public Set<String> retrieveFromCache(String prefix) {
        return redisTemplate.boundSetOps(prefix).members();

    }
}

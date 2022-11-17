package com.zhengqi.wiki03.util;

import com.sun.org.apache.bcel.internal.generic.LOR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
     private final static Logger LOG = LoggerFactory.getLogger(RedisUtil.class);

     @Resource
    private RedisTemplate redisTemplate;

     public boolean validateRepeat(String key, long second) {
         if(redisTemplate.hasKey(key)) {
             LOG.info("key 已存在：{}", key);
             return false;
         } else {
             LOG.info("key 不存在， 放入：{}, 过期 {} 秒", key, second);
             redisTemplate.opsForValue().set(key, key, second, TimeUnit.SECONDS);
             return true;
         }

     }
}

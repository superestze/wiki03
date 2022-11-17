package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.domain.Test;
import com.zhengqi.wiki03.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @Resource
    private RedisTemplate redisTemplate;

    private final static Logger LOG = LoggerFactory.getLogger(TestController.class);

    /**
     * GET POST PUT DELETE
     *
     * @return
     */
    @GetMapping("/list")
    public List<Test> list() {
        return testService.list();
    }

    @GetMapping("/redis/get/{key}")
    public Object getRedisKey(@PathVariable Long key) {
        Object object = redisTemplate.opsForValue().get(key);
        LOG.info("key:{}, value: {}", key, object);
        return object;
    }
}

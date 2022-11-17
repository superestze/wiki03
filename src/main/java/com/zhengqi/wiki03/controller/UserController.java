package com.zhengqi.wiki03.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengqi.wiki03.req.UserLoginReq;
import com.zhengqi.wiki03.req.UserQueryReq;
import com.zhengqi.wiki03.req.UserResetPasswordReq;
import com.zhengqi.wiki03.req.UserSaveReq;
import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.UserLoginResp;
import com.zhengqi.wiki03.resp.UserQueryResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.service.UserService;
import com.zhengqi.wiki03.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;
    
     private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    /**
     * GET POST PUT DELETE
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResp<PageResp<UserQueryResp>> list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<PageResp<UserQueryResp>>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody UserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp();
        userService.save(req);
        return resp;
    }
    @PostMapping("/reset-password")
    public CommonResp resetPassword(@RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp();
        userService.resetPassword(req);
        return resp;
    }


    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@RequestBody UserLoginReq req) {
        // req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp();
        UserLoginResp userLoginResp =  userService.login(req);

        long token = snowFlake.nextId();
        LOG.info("生成单点登录Token {}, 并且放入redis 中", token);
        userLoginResp.setToken(token);
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userLoginResp), 3600*24, TimeUnit.SECONDS);
        resp.setContent(userLoginResp);
        return resp;
    }

    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable Long token) {
        CommonResp resp = new CommonResp();
        redisTemplate.delete(token);
        LOG.info("从redis 中删除token");
        return resp;
    }


}

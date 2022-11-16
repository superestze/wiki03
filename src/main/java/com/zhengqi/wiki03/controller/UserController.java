package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.req.UserLoginReq;
import com.zhengqi.wiki03.req.UserQueryReq;
import com.zhengqi.wiki03.req.UserResetPasswordReq;
import com.zhengqi.wiki03.req.UserSaveReq;
import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.UserLoginResp;
import com.zhengqi.wiki03.resp.UserQueryResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

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
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp();
        UserLoginResp userLoginResp =  userService.login(req);
        resp.setContent(userLoginResp);
        return resp;

    }
}

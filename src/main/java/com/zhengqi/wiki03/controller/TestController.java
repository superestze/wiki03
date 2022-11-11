package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.domain.Test;
import com.zhengqi.wiki03.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    /**
     * GET POST PUT DELETE
     *
     * @return
     */
    @GetMapping("/list")
    public List<Test> list() {
        return testService.list();
    }
}

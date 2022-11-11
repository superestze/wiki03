package com.zhengqi.wiki03.service;

import com.zhengqi.wiki03.domain.Test;
import com.zhengqi.wiki03.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}

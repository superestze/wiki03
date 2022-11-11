package com.zhengqi.wiki03.service;

import com.zhengqi.wiki03.domain.Demo;
import com.zhengqi.wiki03.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }
}

package com.zhengqi.wiki03.service;

import com.zhengqi.wiki03.domain.Ebook;
import com.zhengqi.wiki03.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper eEbookMapper;

    public List<Ebook> list() {
        return eEbookMapper.selectByExample(null);
    }
}

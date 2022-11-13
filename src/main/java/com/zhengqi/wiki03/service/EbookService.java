package com.zhengqi.wiki03.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhengqi.wiki03.domain.Ebook;
import com.zhengqi.wiki03.domain.EbookExample;
import com.zhengqi.wiki03.mapper.EbookMapper;
import com.zhengqi.wiki03.req.EbookReq;
import com.zhengqi.wiki03.resp.EbookResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private final static Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper eEbookMapper;

    public PageResp<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooksList = eEbookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());


        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebooksList) {
        //     // EbookResp ebookResp = new EbookResp();
        //     // BeanUtils.copyProperties(ebook, ebookResp);
        //
        //     EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
        //     respList.add(ebookResp);
        // }


        List<EbookResp> list = CopyUtil.copyList(ebooksList, EbookResp.class);
        PageResp<EbookResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
}

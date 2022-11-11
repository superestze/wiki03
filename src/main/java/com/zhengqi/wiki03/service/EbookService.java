package com.zhengqi.wiki03.service;

import com.zhengqi.wiki03.domain.Ebook;
import com.zhengqi.wiki03.domain.EbookExample;
import com.zhengqi.wiki03.mapper.EbookMapper;
import com.zhengqi.wiki03.req.EbookReq;
import com.zhengqi.wiki03.resp.EbookResp;
import com.zhengqi.wiki03.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper eEbookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebooksList = eEbookMapper.selectByExample(ebookExample);

        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebooksList) {
        //     // EbookResp ebookResp = new EbookResp();
        //     // BeanUtils.copyProperties(ebook, ebookResp);
        //
        //     EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
        //     respList.add(ebookResp);
        // }

        List<EbookResp> list = CopyUtil.copyList(ebooksList, EbookResp.class);
        return list;
    }
}

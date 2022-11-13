package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.domain.Ebook;
import com.zhengqi.wiki03.req.EbookReq;
import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.EbookResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    /**
     * GET POST PUT DELETE
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResp<PageResp<EbookResp>> list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<PageResp<EbookResp>>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}

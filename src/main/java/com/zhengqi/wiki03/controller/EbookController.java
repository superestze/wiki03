package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.req.EbookQueryReq;
import com.zhengqi.wiki03.req.EbookSaveReq;
import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.EbookQueryResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.service.EbookService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public CommonResp<PageResp<EbookQueryResp>> list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<PageResp<EbookQueryResp>>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp();
        ebookService.save(req);
        return resp;
    }


    @DeleteMapping("/delete/{id}")
    public CommonResp save(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        ebookService.delete(id);
        return resp;
    }
}

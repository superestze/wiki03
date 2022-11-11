package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.domain.Ebook;
import com.zhengqi.wiki03.resp.CommonResp;
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
    public CommonResp<List<Ebook>> list() {
        CommonResp<List<Ebook>> resp = new CommonResp<List<Ebook>>();
        List<Ebook> list = ebookService.list();
        resp.setContent(list);
        return resp;
    }
}

package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.req.DocQueryReq;
import com.zhengqi.wiki03.req.DocSaveReq;
import com.zhengqi.wiki03.resp.DocQueryResp;
import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;

    /**
     * GET POST PUT DELETE
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResp<PageResp<DocQueryResp>> list(@Valid DocQueryReq req) {
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<PageResp<DocQueryResp>>();
        PageResp<DocQueryResp> list = docService.list(req);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/all")
    public CommonResp all(@Valid DocQueryReq req) {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody DocSaveReq req) {
        CommonResp resp = new CommonResp();
        docService.save(req);
        return resp;
    }


    @DeleteMapping("/delete/{idStr}")
    public CommonResp delete(@PathVariable String idStr) {
        CommonResp resp = new CommonResp();
        List<String> list = Arrays.asList(idStr.split(","));
        docService.delete(list);
        return resp;
    }

    @DeleteMapping("/find-content/{id}")
    public CommonResp findContent(@PathVariable Long id) {
        CommonResp<String> resp = new CommonResp();
        String content =  docService.findContent(id);
        resp.setContent(content);
        return resp;
    }
}

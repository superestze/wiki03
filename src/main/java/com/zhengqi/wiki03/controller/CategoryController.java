package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.req.CategoryQueryReq;
import com.zhengqi.wiki03.req.CategorySaveReq;
import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.CategoryQueryResp;
import com.zhengqi.wiki03.resp.PageResp;
import com.zhengqi.wiki03.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * GET POST PUT DELETE
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResp<PageResp<CategoryQueryResp>> list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<PageResp<CategoryQueryResp>>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/all")
    public CommonResp all(@Valid CategoryQueryReq req) {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp();
        categoryService.save(req);
        return resp;
    }


    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        categoryService.delete(id);
        return resp;
    }
}

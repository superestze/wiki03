package com.zhengqi.wiki03.controller;

import com.zhengqi.wiki03.resp.CommonResp;
import com.zhengqi.wiki03.resp.StatisticResp;
import com.zhengqi.wiki03.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {
    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public CommonResp getStatistic() {
        List<StatisticResp> statisticResp = ebookSnapshotService.getStatistic();
        CommonResp<List<StatisticResp>> commonResp = new CommonResp();
        commonResp.setContent(statisticResp);
        return commonResp;
    }
}

package com.zhengqi.wiki03.service;

import com.zhengqi.wiki03.mapper.EbookSnapshotMapper;
import com.zhengqi.wiki03.mapper.EbookSnapshotMapperCust;
import com.zhengqi.wiki03.resp.StatisticResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookSnapshotService {
    @Resource
    private EbookSnapshotMapperCust ebookSnapshotMapperCust;

    public void genSnapshot() {
        ebookSnapshotMapperCust.genSnapshot();
    }

    public List<StatisticResp> getStatistic() {
        return ebookSnapshotMapperCust.getStatistic();

    }
}

package com.zhengqi.wiki03.mapper;

import com.zhengqi.wiki03.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCust {
    public void genSnapshot();
    public List<StatisticResp> getStatistic();
}

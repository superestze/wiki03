package com.zhengqi.wiki03.service;

import com.zhengqi.wiki03.mapper.EbookSnapshotMapper;
import com.zhengqi.wiki03.mapper.EbookSnapshotMapperCust;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EbookSnapshotService {
    @Resource
    private EbookSnapshotMapperCust ebookSnapshotMapperCust;

    public void genSnapshot() {
        ebookSnapshotMapperCust.genSnapshot();
    }
}

package com.zhengqi.wiki03.job;

import com.zhengqi.wiki03.service.EbookSnapshotService;
import com.zhengqi.wiki03.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EbookSnapshotJob {

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 定时器只有一个线程
     * 只有等上一个执行完成，下一个才会在下一个时间点执行， 错过了就错过了
     */
    private final static Logger LOG = LoggerFactory.getLogger(EbookSnapshotJob.class);

    /**
     * 30 秒更新一次电子书信息
     * @throws InterruptedException
     */
    @Scheduled(cron="1/30 * * * * ?")
    public void doSnapShot() throws InterruptedException {
        // 曾加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("生成今日电子书快照开始");
        long start = System.currentTimeMillis();
        ebookSnapshotService.genSnapshot();
        LOG.info("生成今日电子书结束， 耗时：{}毫秒", System.currentTimeMillis() - start);
    }
}

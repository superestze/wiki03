package com.zhengqi.wiki03.job;

import com.zhengqi.wiki03.service.DocService;
import com.zhengqi.wiki03.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DocJob {

    @Resource
    private DocService docService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 定时器只有一个线程
     * 只有等上一个执行完成，下一个才会在下一个时间点执行， 错过了就错过了
     */
    private final static Logger LOG = LoggerFactory.getLogger(DocJob.class);

    /**
     * 30 秒更新一次电子书信息
     * @throws InterruptedException
     */
    @Scheduled(cron="1/30 * * * * ?")
    public void cron() throws InterruptedException {
        // 曾加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("更新电子书下的文档数据");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束， 耗时：{}毫秒", System.currentTimeMillis() - start);
    }
}

package com.zhengqi.wiki03.job;

import com.zhengqi.wiki03.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DocJob {

    @Resource
    private DocService docService;

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
        docService.updateEbookInfo();
    }
}

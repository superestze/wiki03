package com.zhengqi.wiki03.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestJob {

    /**
     * 定时器只有一个线程
     * 只有等上一个执行完成，下一个才会在下一个时间点执行， 错过了就错过了
     */
    private final static Logger LOG = LoggerFactory.getLogger(TestJob.class);

    @Scheduled(fixedRate = 5000)
    public void simple() throws InterruptedException {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String dateString = format.format(new Date());
        // Thread.sleep(2000);
        LOG.info("每隔5秒执行一次：{}", dateString);
    }

    @Scheduled(cron="*/2 * * * * ?")
    public void cron() throws InterruptedException {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String dateString = format.format(new Date());
        // Thread.sleep(1500);
        LOG.info("每隔2秒执行一次：{}", dateString);
    }
}

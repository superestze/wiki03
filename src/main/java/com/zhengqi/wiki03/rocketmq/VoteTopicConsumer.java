package com.zhengqi.wiki03.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(consumerGroup = "default", topic = "VOTE_TOPIC")
public class VoteTopicConsumer implements RocketMQListener<MessageExt> {
    private final static Logger LOG = LoggerFactory.getLogger(VoteTopicConsumer.class);


    /**
     * 发送消息
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
      byte[] body = messageExt.getBody();
      LOG.info("ROCKETMQ 接收消息：{}", new String(body));
    }
}

package com.web3.blockToolBox.discord.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web3.blockToolBox.common.RestTemplateUtils;
import com.web3.blockToolBox.discord.entity.BotStartParam;
import com.web3.blockToolBox.discord.entity.Channel;
import com.web3.blockToolBox.discord.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DiscordService {
    private static final String sendMessageUrlTemplate = "https://discord.com/api/v9/channels/%s/messages";

    private static final ScheduledExecutorService timeTaskExecutor = Executors.newScheduledThreadPool(8);

    public void sendCircleMessage(BotStartParam param) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Random random = new Random();
        timeTaskExecutor.scheduleAtFixedRate(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("authorization", param.getAuthorization());
            headers.add("content-type", "application/json");

            List<Channel> channels = param.getChannels();
            String url;
            Channel channel;
            if (channels.size() == 1) {
                channel = channels.get(0);
            } else {
                channel = channels.get(random.nextInt(channels.size()));
            }
            url = String.format(sendMessageUrlTemplate, channel.getId());
            RestTemplateUtils.postForObject(url,
                    JSON.toJSONString(Message.produce(queue, channel.getLanguage())),
                    headers, JSONObject.class);

        }, 1000, param.getDuration(), TimeUnit.MILLISECONDS);
    }
}

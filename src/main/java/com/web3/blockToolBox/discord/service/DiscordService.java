package com.web3.blockToolBox.discord.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web3.blockToolBox.common.RestTemplateUtils;
import com.web3.blockToolBox.discord.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DiscordService {
    private static final String sendMessageUrlTemplate = "https://discord.com/api/v9/channels/%s/messages";

    private static final ScheduledExecutorService timeTaskExecutor = Executors.newScheduledThreadPool(8);

    public void sendCircleMessage(String channelId, String language, long duration, String token) throws InterruptedException {
        String url = String.format(sendMessageUrlTemplate, channelId);
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        timeTaskExecutor.scheduleAtFixedRate(() -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("authorization", token);
            headers.add("content-type", "application/json");
            RestTemplateUtils.postForObject(url,
                    JSON.toJSONString(Message.produce(queue, language)),
                    headers, JSONObject.class);
        }, 1000, duration, TimeUnit.MILLISECONDS);
    }
}

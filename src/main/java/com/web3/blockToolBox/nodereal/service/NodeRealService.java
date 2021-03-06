package com.web3.blockToolBox.nodereal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web3.blockToolBox.common.RestTemplateUtils;
import com.web3.blockToolBox.entity.NodeRealRequestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class NodeRealService {
    public static final String nodeRealUrl = "https://bsc-mainnet.nodereal.io/v1/96e0e79a1b8644e098397c4e0ff8a5e5";

    private static final ScheduledExecutorService timeTaskExecutor = Executors.newScheduledThreadPool(4);

    public static final String[] params = {};

    public static final HttpHeaders headers = new HttpHeaders();

    public static String request = null;

    @PostConstruct
    public void init() {
        headers.add("content-type", "application/json");
        request = JSON.toJSONString(new NodeRealRequestEntity().setJsonrpc("2.0")
                .setId(1).setMethod("eth_gasPrice").setParams(params));
    }
    
    public void startNodeRealRequestBot() {
        timeTaskExecutor.scheduleAtFixedRate(() -> RestTemplateUtils.postForObject(nodeRealUrl,
                request, headers, JSONObject.class), 1000, 300, TimeUnit.MILLISECONDS);
    }
}

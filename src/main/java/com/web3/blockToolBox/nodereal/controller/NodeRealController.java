package com.web3.blockToolBox.nodereal.controller;

import com.web3.blockToolBox.common.ResponseUtils;
import com.web3.blockToolBox.discord.service.DiscordService;
import com.web3.blockToolBox.nodereal.service.NodeRealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/node-real")
public class NodeRealController {

    @Resource
    private NodeRealService nodeRealService;

    @GetMapping("/bot/request/start")
    public ResponseEntity<?> startNodeRealRequestBot() {
        nodeRealService.startNodeRealRequestBot();
        return ResponseUtils.success("正在定时访问NodeReal API");
    }
}

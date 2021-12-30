package com.web3.blockToolBox.discord.controller;

import com.web3.blockToolBox.common.ResponseUtils;
import com.web3.blockToolBox.discord.service.DiscordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/discord")
public class DiscordController {

    @Resource
    private DiscordService discordService;

    @GetMapping("/bot/{channelId}/message/start")
    public ResponseEntity<?> startDiscordMessageBot(@PathVariable("channelId") String channelId,
                                                    @RequestParam("language") String language,
                                                    @RequestParam("duration") Long duration,
                                                    @RequestParam("authorization") String authorization) throws InterruptedException {
        discordService.sendCircleMessage(channelId, language, duration, authorization);
        return ResponseUtils.success("正在每隔" + duration + "毫秒随机发送一次消息至Channel: " + channelId);
    }
}

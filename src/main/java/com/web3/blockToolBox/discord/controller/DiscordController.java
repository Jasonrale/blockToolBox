package com.web3.blockToolBox.discord.controller;

import com.web3.blockToolBox.common.ResponseUtils;
import com.web3.blockToolBox.discord.entity.BotStartParam;
import com.web3.blockToolBox.discord.service.DiscordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/discord")
public class DiscordController {

    @Resource
    private DiscordService discordService;

    @PostMapping("/bot/message/start")
    public ResponseEntity<?> startDiscordMessageBot(@RequestBody BotStartParam param) throws InterruptedException {
        discordService.sendCircleMessage(param);
        return ResponseUtils.success("正在每隔" + param.getDuration() / 1000 + "秒随机发送一次消息至Channel: " + param.getChannelId());
    }
}

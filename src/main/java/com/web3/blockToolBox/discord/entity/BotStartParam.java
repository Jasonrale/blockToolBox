package com.web3.blockToolBox.discord.entity;

import lombok.Data;

import java.util.List;

@Data
public class BotStartParam {
    private List<Long> channelId;

    private String language;

    private Long duration;

    private String authorization;
}

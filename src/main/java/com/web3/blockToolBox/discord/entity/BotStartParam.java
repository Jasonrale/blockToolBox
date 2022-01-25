package com.web3.blockToolBox.discord.entity;

import lombok.Data;

import java.util.List;

@Data
public class BotStartParam {
    private List<Channel> channels;

    private Long duration;

    private String authorization;
}

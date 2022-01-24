package com.web3.blockToolBox.discord.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Queue;
import java.util.Random;

@Data
@Accessors(chain = true)
public class Message {
    private static final String [] RANDOM_CONTENT_CN = {
            "冲冲冲", "卷起来", "坚持住", "等白名单", "卷饿了我",
            "再加把劲冲", "升级真特么慢", "努力肝白名单", "啊这", "你们升得好快啊",
            "nice", "哈哈哈哈", "优秀", "确实是这样", "666",
            "有点累了", "真的么", "想要白名单", "233333", "肝硬化了",
            "慢慢来吧", "加油啊，一起卷", "升得好慢啊", "都是大佬啊",
            "大佬们别这么肝啊", "肝动天，肝动地，就是肝动不了你"};

    private static final String [] RANDOM_CONTENT_EN = {
            "Chatting all day", "Take your time", "I 'm coming", "Cute puppy", "Welcome to there",
            "Let's go!", "Great project", "WAGMI", "Hold on", "Yeah",
            "Nice", "lol", "Good luck", "It's too slow", "Let's Talk to white",
            "I 'm tired", "Come on", "Can't stop", "That's right", "This NFT is really nice",
            "To the moon", "I want wl", "GM", "Let America Great Again!"};

    private String content;

    private String nonce;

    private Boolean tts;

    public static Message produce(Queue<String> queue, String language) {
        Random random = new Random();
        Message message = new Message()
                .setNonce("859436" + (random.nextInt(888888) + 100000) + "356314")
                .setTts(false);

        String content = "Hello";
        boolean flag = true;
        do {
            for (String text : queue) {
                if (content.equals(text)) {
                    content = language.equals("cn") ? RANDOM_CONTENT_CN[random.nextInt(RANDOM_CONTENT_CN.length - 1)]
                     : RANDOM_CONTENT_EN[random.nextInt(RANDOM_CONTENT_EN.length - 1)];
                    flag = false;
                    break;
                }

                flag = true;
            }

        } while (!flag);

        if (queue.size() == 20) {
            queue.remove();
        }
        queue.offer(content);

        return message.setContent(content);
    }
}

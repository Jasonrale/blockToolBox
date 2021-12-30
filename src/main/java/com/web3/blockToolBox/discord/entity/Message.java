package com.web3.blockToolBox.discord.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

@Data
@Accessors(chain = true)
public class Message {
    private static final String [] RANDOM_CONTENT_CN = {
            "冲冲冲", "卷起来", "坚持住", "等白名单", "卷饿了我",
            "再加把劲冲", "升级真特么慢", "努力肝白名单", "啊这", "你们升得好快啊",
            "nice", "哈哈哈哈", "优秀", "确实是这样", "666",
            "有点累了", "真的么", "想要白名单", "233333", "肝硬化了",
            "慢慢来吧", "加油啊，一起卷", "升得好慢啊", "都是大佬啊",
            "大佬们别这么肝啊", "还有一半白名单", "画画太难了", "这个nft挺好看",
            "任务太难了", "好难邀请", "肝动天，肝动地，就是肝动不了你"};

    private static final String [] RANDOM_CONTENT_EN = {
            "Chatting all day", "well", "I 'm coming", "cute puppy", "welcome to there",
            "Let 's go!", "great project", "wagmi", "hodl", "yeah",
            "nice", "lol", "2 minute is too long", "yes", "talk to earn",
            "I 'm tired", "what", "can't stop", "welcome", "233333",
            "to the moon", "I want wl"};

    private static final Queue<String> queueCN = new LinkedBlockingQueue<>();

    private static final Queue<String> queueEN = new LinkedBlockingQueue<>();

    private String content;

    private String nonce;

    private Boolean tts;

    public static Message MessageCN() {
        Random random = new Random();
        Message message = new Message()
                .setNonce("839436" + (random.nextInt(888888) + 100000) + "356314")
                .setTts(false);

        String content = RANDOM_CONTENT_CN[random.nextInt(RANDOM_CONTENT_CN.length - 1)];
        boolean flag = true;
        do {
            for (String text : queueCN) {
                if (content.equals(text)) {
                    content = RANDOM_CONTENT_CN[random.nextInt(RANDOM_CONTENT_CN.length - 1)];
                    flag = false;
                    break;
                }

                flag = true;
            }

        } while (!flag);

        if (queueCN.size() == 20) {
            queueCN.remove();
        }
        queueCN.offer(content);
        return message.setContent(content);
    }

    public static Message MessageEn() {
        Random random = new Random();
        Message message = new Message()
                .setNonce("839436" + (random.nextInt(888888) + 100000) + "356314")
                .setTts(false);

        String content = RANDOM_CONTENT_EN[random.nextInt(RANDOM_CONTENT_EN.length - 1)];
        boolean flag = true;
        do {
            for (String text : queueEN) {
                if (content.equals(text)) {
                    content = RANDOM_CONTENT_EN[random.nextInt(RANDOM_CONTENT_EN.length - 1)];
                    flag = false;
                    break;
                }

                flag = true;
            }

        } while (!flag);

        if (queueEN.size() == 20) {
            queueEN.remove();
        }
        queueEN.offer(content);
        return message.setContent(content);
    }
}

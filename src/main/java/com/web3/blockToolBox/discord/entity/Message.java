package com.web3.blockToolBox.discord.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Queue;
import java.util.Random;

@Data
@Accessors(chain = true)
public class Message {
    private static final String [] RANDOM_CONTENT_CN1 = {
            "最近亏麻了", "卷起来", "坚持住", "等白名单", "好活跃啊大家",
            "再加把劲冲", "活跃起来", "Be active", "努力肝白名单", "啊这",
            "加油啊 現在开始都不算晚", "哈哈哈哈", "厉害了", "确实是这样", "Good Luck",
            "想要白名单", "233333", "还有很多白名单", "卧槽",
            "慢慢来吧", "加油啊，一起卷", "升得好慢啊", "大佬们等等我",
            "肝动天，肝动地，就是肝动不了你", "I want WL",
            "消息发的好快", "Build", "你们升得好快啊", "高质量发言"};

    private static final String [] RANDOM_CONTENT_CN2 = {
            "最近亏麻了", "抓抓机器人给社区净化空气", "好活跃啊大家",
            "努力就会有收获，加油", "邀请太难了", "消息发的好快",
            "还有一大半的白名单", "想要白名单", "加油啊 現在开始都不算晚",
            "慢慢来吧", "加油啊，一起建设社群", "请大家高质量发言",
            "1. 积极帮助他人，为社区翻译英文内容，指导新人，解答他人问题。\n" +
                    "2. 举报机器人（恶意举报会被反噬)，举报广告，举报故意破坏社区坏境的用户。\n" +
                    "3. 创作作品（等最新艺术公布以后）创作不只是仅限于绘画，有人骑车环行，有人站街宣传横幅，有人录视频制作手工艺品，剪纸，制作蛋糕，写诗，甚至作曲，我们鼓励参与者的奇思妙想和用心付出。活跃是最普遍的一种参与方式。\n" +
                    "4 .举报白名单交易行为（一经核实，他的白名单就是你的）。\n" +
                    "5. 高质量聊天，维护和谐良好的社区环境，不可以刷屏和水发言。\n" +
                    "6. 有困难邀请40人的朋友们，你们还是有机会获得白名单的，不要放弃。做一些你力所能及的贡献，只要你用心我们都会感受到。\n" +
                    "\n" +
                    "做到以上其中内容，我们通过审核以后。你将有资格获得珍贵的白名单！感谢你为Squiggle 社区作出的贡献。",
                    "Squiggles 路線圖\n" +
                            "第1階段： \n" +
                            "1）Squiggle 會舉辦 AMA \n" +
                            "2）組織活動 \n" +
                            "3）並始終保持 100% 的透明度 \n" +
                            "\n" +
                            "第2階段： Squiggle 持有者可以創建真正有效的被動收入來源的版本。 \n" +
                            "1）項目方目前的計劃是將所有二級市場特許權使用費收入的 15% 存入 Squiggles 國庫。 \n" +
                            "2）持有人將有機會申請社區內的職位。\n" +
                            "3）不同的職位根據貢獻可以每兩週支付一次的收入，創造更強大的社區動力，確保項目的長期穩定性。\n" +
                            "\n" +
                            "第3階段： Squiggle 持有者將會獲得其他NFT 獨家白名單 \n" +
                            "\n" +
                            "第 4 階段：為 Squiggle 持有者建立私人網絡組，彌合元宇宙與現實世界之間的差距！成員可以在元宇宙與人合作並開始新的企業。 項目方允許持有者對其 Squiggle 擁有完整的知識產權，並且只要符合項目方的指導方針，就可以自由地做他們想做的任何事情。 成員可以創建 Squiggles 的衍生作品，如果作品好項目方會支持，為其母系列（squiggles ）帶來更多的目光和關注！ 項目方會保留二手市場15%的收入去資助 squiggles 社區喜歡的NFT項目，幫助發展。 \n" +
                            "\n" +
                            "第5階段： Squiggle 商店計劃成為一個市場，成員可以在其中購買實體商品以及數字化、元宇宙的商品。 項目方計劃為所有支持者提供從 T 恤到咖啡杯的所有物品！ Squiggle 持有者也可以從 squiggles 商店的所有商品中獲得折扣！"};

    private static final String [] RANDOM_CONTENT_EN = {
            "Chatting all day", "I 'm coming", "Welcome",
            "Let's go!", "Great project", "WAGMI", "Hold on", "Yeah",
            "Nice", "lol", "Good luck", "It's too slow", "Be active",
            "Come on", "Can't stop", "That's right", "This NFT is really nice",
            "To the moon", "I want wl", "GM"};

    private String content;

    private String nonce;

    private Boolean tts;

    public static Message produce(Queue<String> queue, String language) {
        Random random = new Random();
        Message message = new Message()
                .setNonce("859436" + (random.nextInt(888888) + 100000) + "356314")
                .setTts(false);

        String content = "好多人啊";
        boolean flag = true;
        do {
            for (String text : queue) {
                if (content.equals(text)) {
                    content = language.equals("cn") ? RANDOM_CONTENT_CN1[random.nextInt(RANDOM_CONTENT_CN1.length - 1)]
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

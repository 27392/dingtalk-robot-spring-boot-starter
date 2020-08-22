package cn.haohaoli.dingtalk.robot.msg;

import cn.haohaoli.dingtalk.robot.msg.at.AtMsg;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiWenHao
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MarkdownMsg extends AbstractMsg {

    private AtMsg at;

    private Content markdown;

    public MarkdownMsg(String title, String text) {
        super(MsgType.MARKDOWN.getValue());
        markdown = new Content(title, text);
    }

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    private static class Content {

        private final String title;

        private final String text;
    }
}

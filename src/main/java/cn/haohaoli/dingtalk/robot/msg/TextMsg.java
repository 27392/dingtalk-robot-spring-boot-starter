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
public class TextMsg extends AbstractMsg {

    private AtMsg at;

    private Content text;

    public TextMsg(String content) {
        this(null, content);
    }

    public TextMsg(AtMsg at, String content) {
        super(MsgType.TEXT.getValue());
        this.text = new Content(content);
        this.at = at;
    }

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    private static class Content {
        private final String content;
    }

}

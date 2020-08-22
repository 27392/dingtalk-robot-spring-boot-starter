package cn.haohaoli.dingtalk.robot.msg;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 消息类型
 * @author LiWenHao
 * @see <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq">官方文档</a>
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum MsgType {

    /**
     * @see <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/e9d991e2">Additional HTTP Status Codes</a>
     */
    TEXT("text"),

    /**
     * @see <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/404d04c3">Additional HTTP Status Codes</a>
     */
    LINK("link"),

    /**
     * @see <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/9e91d73c">Additional HTTP Status Codes</a>
     */
    MARKDOWN("markdown"),

    /**
     * @see <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/0fa88adc">Additional HTTP Status Codes</a>
     */
    ACTION_CARD("actioncard"),

    /**
     * @see <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/0a5150ec">Additional HTTP Status Codes</a>
     */
    FEED_CARD("feedcard"),
    ;

    private final String value;


}


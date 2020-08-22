package cn.haohaoli.dingtalk.robot.util;

import cn.haohaoli.dingtalk.robot.config.DingTalkRobotProperties;
import cn.haohaoli.dingtalk.robot.http.OkHttpHelper;
import cn.haohaoli.dingtalk.robot.msg.AbstractMsg;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * 机器人工具类
 *
 * @author LiWenHao
 */
@Slf4j
@UtilityClass
public class RobotHelper {

    /**
     * 发送消息
     *
     * @param msg 消息
     */
    public void sendMsg(AbstractMsg msg) {
        if (Objects.isNull(msg)) {
            return;
        }
        DingTalkRobotProperties properties;
        try {
            properties = SpringApplicationContextHolder.getBean(DingTalkRobotProperties.class);
        } catch (Exception e) {
            log.error("获取钉钉机器人配置失败", e);
            return;
        }
        try {
            OkHttpHelper.doPost(properties.getWebHookUrl(), msg);
        } catch (IOException e) {
            log.error("发送钉钉消息失败", e);
        }
    }

}

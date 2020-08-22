package cn.haohaoli.dingtalk.robot.log.handle;

import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.haohaoli.dingtalk.robot.msg.AbstractMsg;

/**
 * @author LiWenHao
 */
public interface MsgHandle {

    /**
     * 获取消息
     * @param event 事件
     * @return
     */
    AbstractMsg getMsg(ILoggingEvent event);

}

package cn.haohaoli.dingtalk.robot.log.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import cn.haohaoli.dingtalk.robot.log.handle.impl.DefaultMsgHandle;
import cn.haohaoli.dingtalk.robot.log.handle.MsgHandle;
import cn.haohaoli.dingtalk.robot.util.RobotHelper;
import lombok.Getter;
import lombok.Setter;

/**
 * 钉钉机器人Appender
 *
 * @author LiWenHao
 */
@Getter
@Setter
public class DingTalkRobotAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    /**
     * 消息处理器
     */
    private MsgHandle handle = new DefaultMsgHandle();

    @Override
    protected void append(ILoggingEvent event) {
        RobotHelper.sendMsg(handle.getMsg(event));
    }

}

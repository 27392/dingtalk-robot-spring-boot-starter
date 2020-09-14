package cn.haohaoli.dingtalk.robot.log.handle.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import cn.haohaoli.dingtalk.robot.config.DingTalkRobotProperties;
import cn.haohaoli.dingtalk.robot.log.handle.MsgHandle;
import cn.haohaoli.dingtalk.robot.msg.AbstractMsg;
import cn.haohaoli.dingtalk.robot.msg.MarkdownMsg;
import cn.haohaoli.dingtalk.robot.util.SpringApplicationContextHolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author LiWenHao
 */
public class DefaultMsgHandle implements MsgHandle {

    private static final  DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public AbstractMsg getMsg(ILoggingEvent event) {
        if (Level.ERROR == event.getLevel()) {

            String msg = Optional.ofNullable(event.getThrowableProxy())
                    .map(it -> (ThrowableProxy) it)
                    .map(ThrowableProxy::getThrowable)
                    .map(this::getStackTraceInfo)
                    .orElseGet(event::getFormattedMessage);

            String sb = "## 错误日志"
                    + "\n ### 应用名称"
                    + "\n" + SpringApplicationContextHolder.getBean(DingTalkRobotProperties.class).getName()
                    + "\n ### 等级"
                    + "\n" + Level.ERROR.levelStr
                    + "\n### 类名"
                    + "\n" + event.getLoggerName()
                    + "\n### 时间"
                    + "\n" + FORMATTER.format(LocalDateTime.now())
                    + "\n### 信息"
                    + "\n> " + msg;

            return new MarkdownMsg("错误日志", sb);
        }
        return null;
    }

    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return
     */
    private String getStackTraceInfo(Throwable throwable) {
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        } catch (IOException e) {
            // 忽略
            return "";
        }
    }

}

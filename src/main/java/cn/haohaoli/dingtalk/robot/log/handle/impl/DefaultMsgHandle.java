package cn.haohaoli.dingtalk.robot.log.handle.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import cn.haohaoli.dingtalk.robot.log.handle.MsgHandle;
import cn.haohaoli.dingtalk.robot.msg.AbstractMsg;
import cn.haohaoli.dingtalk.robot.msg.MarkdownMsg;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author LiWenHao
 */
public class DefaultMsgHandle implements MsgHandle {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public AbstractMsg getMsg(ILoggingEvent event) {
        if (Level.ERROR == event.getLevel()) {
            ThrowableProxy throwableProxy = (ThrowableProxy) event.getThrowableProxy();
            Throwable      throwable      = throwableProxy.getThrowable();
            String sb = "### 错误日志"
                    + "\n #### 等级"
                    + "\n" + Level.ERROR.levelStr
                    + "\n#### 类名"
                    + "\n" + event.getLoggerName()
                    + "\n#### 时间"
                    + "\n" + dateTimeFormatter.format(LocalDateTime.now())
                    + "\n#### 错误信息"
                    + "\n" + throwable.getMessage()
                    + "\n#### 堆栈信息"
                    + "\n> " + getStackTraceInfo(throwable);
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

<p align="center">
	<strong>一个基于钉钉机器人实现简单日志监控的SpringBoot启动器</strong>
</p>

<p align="center">
    <img src="https://blog-haohaoli.oss-cn-chengdu.aliyuncs.com/other/20200822222346.jpg" border="0" />
</p>

## 简介

该项目是一个简单的日志监控项目,使用钉钉机器人对项目日志监控,当输出的日志满足我们定义的条件后会将其发送至钉钉群聊,达到简单的监控

例如,可以在当产生`ERROR`等级日志时将堆栈信息等信息发送至钉钉群调,可以省去去服务器上看日志的时间

### 使用

首先获取到**自定义机器人webhook**,[点我前往官方文档](`https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq`)

将本项目克隆到本地后在项目根目录执行`mvn install`命令.然后将依赖粘贴至其他项目中即可

```xml
<dependency>
    <groupId>cn.haohaoli</groupId>
    <artifactId>dingtalk-robot-spring-boot-starter</artifactId>
    <version>1.0.0-RELEASE</version>
</dependency>
```

### 参数配置

1.配置文件

```yaml
ding-talk:
  robot:
    enable: true  // 是否启用
    access-token: // 填写你的accessToken
```

2.`logback`配置

新增`cn.haohaoli.dingtalk.robot.log.appender.DingTalkRobotAppender`类型的`Appender`

然后将添加至`root`标签内

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>

    <include resource="org/springframework/boot/logging/logback/defaults.xml" />

    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
    
    // 添加 appender
    <appender name="DINGTALK" class="cn.haohaoli.dingtalk.robot.log.appender.DingTalkRobotAppender"/>

    <root level="info">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="DINGTALK"/>  // 添加 root标签内
    </root>

</configuration>
```

### 自定义处理

默认只输出`ERROR`等级日志至机器人

如果需要自定义操作

   1. 继承`cn.haohaoli.dingtalk.robot.log.handle.MsgHandle`接口,在`getMsg`方法自定义逻辑
   2. 在`DingTalkRobotAppender`,`appender`中添加标签`<handle class="实现了MsgHandle接口的类"/>`

> 注意! 每个机器人每分钟最多发送20条。如果超过20条，会限流10分钟.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>

    <include resource="org/springframework/boot/logging/logback/defaults.xml" />

    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />

    <appender name="DINGTALK" class="cn.haohaoli.dingtalk.robot.log.appender.DingTalkRobotAppender">
        <handle class="实现了MsgHandle接口的类"/>
    </appender>

    <root level="info">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="DINGTALK"/>
    </root>

</configuration>
```
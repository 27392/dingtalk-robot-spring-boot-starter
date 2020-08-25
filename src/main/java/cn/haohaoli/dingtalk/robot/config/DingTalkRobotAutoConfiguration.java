package cn.haohaoli.dingtalk.robot.config;

import cn.haohaoli.dingtalk.robot.constant.Constants;
import cn.haohaoli.dingtalk.robot.util.SpringApplicationContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * @author LiWenHao
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = Constants.ENABLE, havingValue = "true")
@EnableConfigurationProperties(value = DingTalkRobotProperties.class)
public class DingTalkRobotAutoConfiguration {

    private final DingTalkRobotProperties properties;

    @PostConstruct
    public void init() {
        Assert.notNull(properties.getAccessToken(), "请配置钉钉机器人accessToken");
        log.info("以开启钉钉机器人...");
    }

    @Bean
    public SpringApplicationContextHolder springApplicationContextHolder() {
        return new SpringApplicationContextHolder();
    }

}

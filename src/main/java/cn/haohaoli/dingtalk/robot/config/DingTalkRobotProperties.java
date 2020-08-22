package cn.haohaoli.dingtalk.robot.config;

import cn.haohaoli.dingtalk.robot.constant.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 钉钉机器人配置
 * @author LiWenHao
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = Constants.ROBOT_CONFIG_PREFIX)
public class DingTalkRobotProperties {

    private Boolean enable = Boolean.FALSE;

    private String accessToken;

    public String getWebHookUrl(){
        return String.format(Constants.WEB_HOOK_URL, accessToken);
    }

}

package cn.haohaoli.dingtalk.robot.constant;

/**
 * @author LiWenHao
 */
public interface Constants {

    String ROBOT_CONFIG_PREFIX = "ding-talk.robot";

    String ENABLE = ROBOT_CONFIG_PREFIX + "." + "enable";

    String WEB_HOOK_URL = "https://oapi.dingtalk.com/robot/send?access_token=%s";
}

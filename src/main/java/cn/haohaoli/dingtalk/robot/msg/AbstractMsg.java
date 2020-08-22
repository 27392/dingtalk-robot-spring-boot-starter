package cn.haohaoli.dingtalk.robot.msg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiWenHao
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AbstractMsg {

    private final String msgtype;

}

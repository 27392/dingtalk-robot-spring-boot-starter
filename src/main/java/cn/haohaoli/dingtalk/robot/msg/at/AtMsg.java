package cn.haohaoli.dingtalk.robot.msg.at;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiWenHao
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtMsg {

    private List<String> atMobiles = new ArrayList<>();

    private Boolean atAll = Boolean.FALSE;
}

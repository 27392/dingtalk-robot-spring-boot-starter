package cn.haohaoli.dingtalk.robot.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * @author LiWenHao
 */
@Slf4j
@UtilityClass
public class OkHttpHelper {

    private final OkHttpClient HTTP_CLIENT;

    //每个机器人每分钟最多发送20条。如果超过20条，会限流10分钟。
    // https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq\
    private final RateLimiter LIMITER = RateLimiter.create(17.0 / 60);

    private final ObjectMapper objectMapper = new ObjectMapper();

    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    static {
        HTTP_CLIENT = new OkHttpClient.Builder().build();
    }

    /**
     * 发送POST请求
     *
     * @param url   url
     * @param param 参数
     * @throws IOException
     */
    public void doPost(String url, Object param) throws IOException {

        double acquire = LIMITER.acquire();
        String content = objectMapper.writeValueAsString(param);
        log.debug("request => acquire: {}, param : [{}]", acquire, content);

        RequestBody requestBody = RequestBody.create(mediaType, content);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();

        if (Objects.nonNull(response.body())) {
            log.debug("response <= : [{}]", response.body().string());
        }
    }
}

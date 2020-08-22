package cn.haohaoli.dingtalk.robot.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

/**
 * @author LiWenHao
 */
@Slf4j
@UtilityClass
public class OkHttpHelper {

    private final OkHttpClient HTTP_CLIENT;

    private final ObjectMapper objectMapper = new ObjectMapper();

    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    static {
        HTTP_CLIENT = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    /**
     * 发送POST请求
     *
     * @param url   url
     * @param param 参数
     * @throws IOException
     */
    public void doPost(String url, Object param) throws IOException {

        RequestBody requestBody = RequestBody.create(mediaType, objectMapper.writeValueAsString(param));

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        HTTP_CLIENT.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                log.error("错误", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("{}", response.body());
            }
        });
    }
}

package cn.haohaoli.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author LiWenHao
 */
@Slf4j
@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner sendNotify() {
        return args -> {
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        };
    }
}

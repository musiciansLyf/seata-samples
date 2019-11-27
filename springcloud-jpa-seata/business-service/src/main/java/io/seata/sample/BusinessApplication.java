package io.seata.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusinessApplication {
    //Client 创建的结果是与 TC 的一个 Netty 连接，所以在启动日志中可以看到两个 Netty Channel，其中标明了 transactionRole 分别为 TMROLE 和 RMROLE。
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }

}

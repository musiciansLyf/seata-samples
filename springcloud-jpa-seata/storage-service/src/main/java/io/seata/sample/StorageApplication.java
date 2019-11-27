package io.seata.sample;

import com.alibaba.druid.pool.DruidDataSource;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {//，同时加入了日志监控，可以很好的监控DB池连接和SQL的执行情况
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    /*
     * 使用 DataSourceProxy 的目的是为了引入 ConnectionProxy ，fescar 无侵入的一方面就体现在 ConnectionProxy 的实现上，
     * 即分支事务加入全局事务的切入点是在本地事务的 commit 阶段，这样设计可以保证业务数据与 undo_log 是在一个本地事务中。Fescar（四月初更名为Seata）
     *
     * undo_log 是需要在业务库上创建的一个表，fescar 依赖该表记录每笔分支事务的状态及二阶段 rollback 的回放数据。
     * 不用担心该表的数据量过大形成单点问题，在全局事务 commit 的场景下事务对应的 undo_log 会异步删除。
     */
    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSource(DruidDataSource druidDataSource) {//对源数据的代理
        return new DataSourceProxy(druidDataSource);
    }

}

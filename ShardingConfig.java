
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.single.api.config.SingleRuleConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class ShardingConfig {

    @Resource
    private DataSource1Config dataSource1Config;
    @Resource
    private DataSource2Config dataSource2Config;


    @Bean(name = "shardingDataSource")
    public DataSource getDataSource() throws SQLException {
       // 1. 创建数据源映射
        Map<String, DataSource> dataSourceMap = createDataSource();

        // 2. 创建数据源
       return ShardingSphereDataSourceFactory.createDataSource(xxxxx);
    }


    public  Map<String, DataSource> createDataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName(dataSource1Config.getDriverClassName());
        dataSource1.setJdbcUrl(dataSource1Config.getUrl());
        dataSource1.setUsername(dataSource1Config.getUsername());
        dataSource1.setPassword(dataSource1Config.getPassword());
        dataSource1.setMaxLifetime(dataSource1Config.getMaxLifeTime());
        dataSource1.setConnectionTimeout(dataSource1Config.getConnectionTimeout());

        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName(dataSource2Config.getDriverClassName());
        dataSource2.setJdbcUrl(dataSource2Config.getUrl());
        dataSource2.setUsername(dataSource2Config.getUsername());
        dataSource2.setPassword(dataSource2Config.getPassword());

        dataSourceMap.put(dataSource1Config.getName(), dataSource1);
        dataSourceMap.put(dataSource2Config.getName(), dataSource2);
        return dataSourceMap;
    }

}
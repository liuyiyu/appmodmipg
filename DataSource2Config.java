import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DataSource2Config {

    @Value("${spring.shardingsphere.dataSource2.name}")
    private String name;
    @Value("${spring.shardingsphere.dataSource2.driver-class-name}")
    private String driverClassName;
    @Value("${spring.shardingsphere.dataSource2.url}")
    private String url;
    @Value("${spring.shardingsphere.dataSource2.username}")
    private String username;
    @Value("${spring.shardingsphere.dataSource2.password}")
    private String password;
}

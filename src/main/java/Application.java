import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Nott
 * @Date 2023/8/9
 */

@MapperScan(basePackages = "com.nott")
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.nott")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.nott")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

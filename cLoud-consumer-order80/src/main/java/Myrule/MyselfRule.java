package Myrule;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//要放在不被spring扫描的包下，这样才能做到定制化
public class MyselfRule {

    @Bean
    public IRule MyRule(){
        return new RandomRule();
    }
}

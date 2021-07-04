package pl.bykowski.springsecuritysimplefactoryauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringsecuritySimpleFactoryAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritySimpleFactoryAuthApplication.class, args);
    }

}

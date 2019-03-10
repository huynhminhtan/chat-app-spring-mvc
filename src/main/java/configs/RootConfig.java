package main.java.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"main.java.configs"})
public class RootConfig {

    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleFirstDayOfMonthTask() {

    }
}
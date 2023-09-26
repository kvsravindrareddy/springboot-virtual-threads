package com.veera.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class CustomThreadExecutorConfig {

//    @Bean
//    public Executor virtualThreadExecutor() {
//        return new VirtualThreadExecutor("VirtualThreadExecutor");
//    }

    @Bean
    public ExecutorService threadTaskExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    @Primary
    public ExecutorService virtualTaskExecutorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

//    @Bean
//    public Executor threadPoolExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setThreadNamePrefix("ThreadPoolTaskExecutor");
//        return executor;
//    }
}

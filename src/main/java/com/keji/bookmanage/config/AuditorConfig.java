package com.keji.bookmanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @auther tangguangchuan
 * @date 2021/1/13 下午2:44
 */
@Configuration
@EnableJpaAuditing
public class AuditorConfig {
    @Bean
    public UserAuditor userAuditor(){
        return new UserAuditor();
    }
}

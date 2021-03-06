package com.home.dshapkin.tracking.things.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DataConfiguration {

    @Bean
    public Database database() {
        return new Database(new HashMap<>());
    }

    @Bean
    public ActivityHandler activityHandler(Database database) {
        return new ActivityHandler(database);
    }
}

package com.example.javeke.ws.config;

import com.example.javeke.ws.converters.ZonedDateTimeMongoReadConverter;
import com.example.javeke.ws.converters.ZonedDateTimeMongoWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class AppConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions(){
        return new MongoCustomConversions(Arrays.asList(
                new ZonedDateTimeMongoReadConverter(),
                new ZonedDateTimeMongoWriteConverter()
        ));
    }
}

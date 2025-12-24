package com.sixoneseven.blocktrain.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.sixoneseven.blocktrain")
@EnableJpaRepositories(basePackages = "com.sixoneseven.blocktrain.repo")
@EntityScan(basePackages = "com.sixoneseven.blocktrain.entity")
public class BlockTrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockTrainApplication.class, args);
    }

}

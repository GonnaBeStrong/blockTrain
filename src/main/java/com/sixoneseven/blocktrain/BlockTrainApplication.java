package com.sixoneseven.blocktrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BlockTrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockTrainApplication.class, args);
    }

}

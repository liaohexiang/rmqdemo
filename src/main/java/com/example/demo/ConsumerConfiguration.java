package com.example.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(ConsumerConfiguration.ConsumerSource.class)
public class ConsumerConfiguration {
    public interface ConsumerSource{

        String DEMO1_INPUT="DEMO1_INPUT";
        String DEMO2_INPUT="DEMO2_INPUT";
        @Input("DEMO1_INPUT")
        SubscribableChannel demo1Input();

        @Input("DEMO2_INPUT")
        SubscribableChannel demo2Input();


    }
}

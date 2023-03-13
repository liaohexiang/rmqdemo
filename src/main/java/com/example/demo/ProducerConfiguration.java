package com.example.demo;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
@EnableBinding(ProducerConfiguration.ProducerSource.class)
public class ProducerConfiguration {
    public interface ProducerSource{
        String DEMO1_OUTPUT="DEMO1_OUTPUT";
        String DEMO2_OUTPUT="DEMO2_OUTPUT";
        @Output("DEMO1_OUTPUT")
        MessageChannel demo1Output();
        @Output("DEMO2_OUTPUT")
        MessageChannel demo2Output();
    }
}

package com.example.demo.route;

import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends org.apache.camel.builder.RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/files?noop=true")
                .log("Moving file: ${header.CamelFileName}")
                .to("file:src/main/resources/processed");
    }
    
}

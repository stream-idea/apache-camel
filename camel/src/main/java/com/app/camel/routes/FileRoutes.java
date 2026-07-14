package com.app.camel.routes;

import org.springframework.stereotype.Component;

@Component
public class FileRoutes extends org.apache.camel.builder.RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/file?noop=true")
                .log("Transfering ${message}, ${header}")
                .to("file:src/main/resources/file/output");
    }

}

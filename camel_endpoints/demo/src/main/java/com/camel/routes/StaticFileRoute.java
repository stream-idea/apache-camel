package com.camel.routes;

import java.util.logging.Logger;

public class StaticFileRoute extends org.apache.camel.builder.RouteBuilder {



    Logger logger = Logger.getLogger(StaticFileRoute.class.getName());

    @Override
    public void configure() throws Exception {
        // example of static endpoint configuration using properties file
        from("file:src/main/resources/files?noop={{camel.property.noop}}&filter=#fileFilter")
                .log("File content: ${body}")
                .to("file:src/main/resources/files/output");
    }

}

package com.camel.routes;

public class DynamicFileRoute extends org.apache.camel.builder.RouteBuilder {

    @Override
    public void configure() throws Exception {
        // example of dynamic endpoint configuration using properties file
        from("file:src/main/resources/files_2?noop={{camel.property.noop}}")
                .log("File content: ${body}")
                .setProperty("destination", simple("${body}"))
                .toD("file:src/main/resources/files_2/output2/${exchangeProperty.destination}")
                .log("Destination: ${exchangeProperty.destination}");
    }

}

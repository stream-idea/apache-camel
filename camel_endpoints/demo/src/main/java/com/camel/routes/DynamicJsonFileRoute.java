package com.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class DynamicJsonFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        // example of dynamic endpoint configuration using properties file
        from("file:src/main/resources/files_3?noop={{camel.property.noop}}&filter=#jsonFileFilter")
                .log("File content: ${body}")
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .setProperty("destination", simple("${body[outFolder]}"))
                .marshal()
                .json(JsonLibrary.Jackson)
                .toD("file:src/main/resources/files_3/output3/${exchangeProperty.destination}?fileName=RAW(&message.json)") // we have used raw because filename starts with &
                .log("Destination: ${exchangeProperty.destination}");
    }

    
}

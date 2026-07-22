package com.messages.route;

import org.apache.camel.builder.RouteBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonProcessor2Route extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("file:{{camel.files.input.path}}?fileName=processor_example_v2.json&noop={{camel.property.noop}}")
                .unmarshal().json()
                .log("Processing JSON file: body: ${body} - headers: ${headers}")
                .process(
                        // implement the processor logic
                        exchange -> {
                            // extract
                            // we directly convert as ObjectNode, because we have already unmarshalled the JSON into a Java object
                            ObjectNode body = exchange.getMessage().getBody(ObjectNode.class);

                            // transform : a java logic of conversion/transformation
                            body.put("additionalInfo", "new information added by the processor");

                            // set the modified body back to the exchange
                            exchange.getMessage().setBody(body);
                        })
                .marshal().json()
                .to("file:{{camel.files.output.path}}?fileName=processor_example_v2.json");
    }

}

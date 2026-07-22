package com.messages.route;

import org.apache.camel.builder.RouteBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonProcessorRoute extends RouteBuilder {

    /**
     * This version  is not properly correct because forces us to create for each message a new ObjectMapper instance, which is not efficient.
     * There is another way using Apache Camel, let's se the version n.2
     */

    @Override
    public void configure() throws Exception {
        from("file:{{camel.files.input.path}}?fileName=processor_example.json&noop={{camel.property.noop}}")
                .log("Processing JSON file: body: ${body} - headers: ${headers}")
                .process(
                        // implement the processor logic
                        exchange -> {
                            // extract
                            String body = exchange.getMessage().getBody(String.class);

                            // transform : a java logic of conversion/transformation
                            ObjectMapper objectMapper = JsonMapper.builder().build();
                            ObjectNode node = objectMapper.readValue(body, ObjectNode.class);
                            node.put("additionalInfo", "new information added by the processor");

                            // set the modified body back to the exchange
                            exchange.getMessage().setBody(node.toPrettyString());
                        })
                .to("file:{{camel.files.output.path}}?fileName=processor_example.json");
    }

}

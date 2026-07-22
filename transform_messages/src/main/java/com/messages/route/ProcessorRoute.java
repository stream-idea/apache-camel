package com.messages.route;

import org.apache.camel.builder.RouteBuilder;

/**
 *
 * ProcessorRoute
 * this route use file component to read the file from the input folder and process it using a custom processor
 */
public class ProcessorRoute  extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:{{camel.files.input.path}}?fileName=processor_example.txt&noop={{camel.property.noop}}")
        .log("Processing file: body: ${body} - headers: ${headers}")
        .process(
            // implement the processor logic
            exchange -> {
                // extract
                String body = exchange.getMessage().getBody(String.class);

                // transform : a java logic of conversion/transformation
                String modifiedBody = body.toUpperCase();

                // set the modified body back to the exchange
                exchange.getMessage().setBody(modifiedBody);
            }
        )
                .to("file:{{camel.files.output.path}}?fileName=processor_example.txt");
    }
    
}

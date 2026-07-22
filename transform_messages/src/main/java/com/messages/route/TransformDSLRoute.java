package com.messages.route;

public class TransformDSLRoute extends org.apache.camel.builder.RouteBuilder {

    @Override
    public void configure() throws Exception {
                from("file:{{camel.files.input.path}}?fileName=transform_example.txt&noop={{camel.property.noop}}")
                .log("Processing JSON file: body: ${body} - headers: ${headers}")
                .transform(body().regexReplaceAll("(?i)\\bhello\\b", "bye"))
                .transform(simple("<request>${body}</request>"))
                .to("file:{{camel.files.output.path}}?fileName=transform_example.txt");
    }
    
}

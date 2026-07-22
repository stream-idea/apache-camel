package com.messages.route;

import org.apache.camel.builder.RouteBuilder;


/**
 * BeanRoute
 * here the transformation logic is delegated to a bean, and we use the bean component to call the bean method
 */
public class BeanRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
                from("file:{{camel.files.input.path}}?fileName=bean_example.json&noop={{camel.property.noop}}")
                .unmarshal().json()
                .log("Processing JSON file: body: ${body} - headers: ${headers}")
                .bean("{{camel.beans.exchangeMapper.name}}", "transform")  //method name can be omitted if the bean has only one method
                .marshal().json()
                .to("file:{{camel.files.output.path}}?fileName=bean_example.json");
    }

}

package com.camel.route;

import org.apache.camel.builder.RouteBuilder;

public class ChoiceRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // CONTENT-BASED ROUTING
        from("jms:input.queue")
            .log("Body: ${body} Headers: ${headers}")
            .choice()
                .when(exchange -> {
                    Object requestType = exchange.getMessage().getHeader("requestType");
                    if(requestType != null) {
                        return "statement".equals(requestType.toString());
                    }
                    return false;
                }).to("jms:statement.queue")
                .when(simple("${header.requestType} == 'paymentDetails'")).to("jms:request.details.queue")
                .otherwise().to("jms:unrecognized.queue")
                .endChoice()
                .log("End Routing");

    }
    
}

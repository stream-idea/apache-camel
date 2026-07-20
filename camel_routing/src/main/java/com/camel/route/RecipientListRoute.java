package com.camel.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.camel.builder.RouteBuilder;

public class RecipientListRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:recipient.list.queue")
                .log("Body: ${body} Headers: ${headers}")
                .process(exchange -> {
                    Object departments = exchange.getMessage().getHeader("departments");
                    List<String> targets = new ArrayList<>();

                    Optional.ofNullable(departments)
                            .ifPresent(d -> Arrays.asList(d.toString().split(","))
                                    .forEach(dept -> targets.add(formatTarget(dept))));

                    exchange.getMessage().setHeader("whereToSend", targets);

                })
                .recipientList(header("whereToSend"))
                .log("End Routing");
    }

    private String formatTarget(String dept) {
        return String.format(
                "jms:%s.queue", dept.trim());
    }

}

package com.messages.bean;

import org.apache.camel.Exchange;

import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * Mapper
 * here we have exported transformation logic from process body
 */
public class ExchangeMapper {

    public void transform(Exchange exchange) {

        // extract
        // we directly convert as ObjectNode, because we have already unmarshalled the
        // JSON into a Java object
        ObjectNode body = exchange.getMessage().getBody(ObjectNode.class);

        // transform : a java logic of conversion/transformation
        body.put("additionalInfo", "new information added by the processor");

        // set the modified body back to the exchange
        exchange.getMessage().setBody(body);

    }

}

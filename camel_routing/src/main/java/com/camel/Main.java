package com.camel;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camel.route.ChoiceRoute;
import com.camel.route.RecipientListRoute;

import jakarta.jms.ConnectionFactory;

public class Main {
    public static void main(String[] args) {

        Logger log = LoggerFactory.getLogger(Main.class);

       
        try(CamelContext context = new DefaultCamelContext()) {

            // setup properties component to load properties from camel.properties
            PropertiesComponent properties = context.getPropertiesComponent();
            properties.setLocation("classpath:camel.properties");
           

            // setup routes components
            List<String> enabledRoutes = getEnabledRoutes(context);
            for (String route : enabledRoutes) {
                switch (route) {
                    case "choice" -> context.addRoutes(new ChoiceRoute());
                    case "recipientlist" -> context.addRoutes(new RecipientListRoute());
                    default -> log.warn("Unknown route: {}", route);
                }
            }

            
            // setup JMS component with connection factory
            JmsComponent jmsComponent = context.getComponent("jms", JmsComponent.class);
            jmsComponent.setConnectionFactory(configureConnectionFactory(context));



            context.start();
            context.resolvePropertyPlaceholders("{{camel.app.lifetime.milliseconds}}");
            Thread.sleep(Long.parseLong(context.resolvePropertyPlaceholders("{{camel.app.lifetime.milliseconds}}")));
            context.stop();
            log.info("Stopping Camel context...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        




    }

    private static List<String> getEnabledRoutes(CamelContext context) {
        Optional<String> enabledRoutes = Optional.ofNullable(context.resolvePropertyPlaceholders("{{camel.app.routes.enabled}}"));

        return enabledRoutes.map(routes -> Arrays.stream(routes.split(","))
                .filter(s-> s!= null && !s.isBlank())
                .map(s -> s.trim())
                .toList())
                .orElse(List.of());
    }

    private static ConnectionFactory configureConnectionFactory(CamelContext context) throws Exception {
        String brokerUrl = context.resolvePropertyPlaceholders("{{artemis.broker.url}}");
        String pass = context.resolvePropertyPlaceholders("{{artemis.broker.password}}");
        String user = context.resolvePropertyPlaceholders("{{artemis.broker.username}}");
        JmsConnectionFactory connectionFactory = new JmsConnectionFactory(brokerUrl);
        // Usa un Client ID dinamico per evitare conflitti se il precedente è ancora connesso
        connectionFactory.setClientID("camel-client-" + System.nanoTime());
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(pass);
        return connectionFactory;
    }
}
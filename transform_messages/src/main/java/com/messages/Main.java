package com.messages;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;

import com.messages.bean.ExchangeMapper;
import com.messages.route.BeanRoute;
import com.messages.route.JsonProcessor2Route;
import com.messages.route.JsonProcessorRoute;
import com.messages.route.ProcessorRoute;
import com.messages.route.TransformDSLRoute;

public class Main {
    public static void main(String[] args) {
        
        try(CamelContext camelContext = new DefaultCamelContext()) {

            // setup properties component to load properties from camel.properties
            PropertiesComponent properties = camelContext.getPropertiesComponent();
            properties.setLocation("classpath:camel.properties");

            // configure route
            camelContext.addRoutes(new ProcessorRoute());
            camelContext.addRoutes(new JsonProcessorRoute());
            camelContext.addRoutes(new JsonProcessor2Route());
            camelContext.addRoutes(new BeanRoute());
            camelContext.addRoutes(new TransformDSLRoute());

            // registry beans
            String exchangeMapperBeanName = camelContext.resolvePropertyPlaceholders("{{camel.beans.exchangeMapper.name}}");
            camelContext.getRegistry().bind(exchangeMapperBeanName, new ExchangeMapper());


            camelContext.start();

            // Keep the application running for a while to allow routes to process messages
            Thread.sleep(Long.parseLong(camelContext.resolvePropertyPlaceholders("{{camel.app.lifetime.milliseconds}}")));
           

            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
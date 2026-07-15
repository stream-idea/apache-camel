package com.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;

import com.camel.bean.FileFilter;
import com.camel.bean.JsonFileFilter;
import com.camel.routes.DynamicFileRoute;
import com.camel.routes.DynamicJsonFileRoute;
import com.camel.routes.StaticFileRoute;

public class Main {
    public static void main(String[] args) {
        try (CamelContext context = new DefaultCamelContext()) {

            // configure properties component
            PropertiesComponent propertiesComponent = context.getPropertiesComponent();
            propertiesComponent.setLocation("classpath:camel.properties");

            // configure routes
            context.addRoutes(new StaticFileRoute());
            context.addRoutes(new DynamicFileRoute());
            context.addRoutes(new DynamicJsonFileRoute());

            context.getRegistry().bind("fileFilter", new FileFilter());
            context.getRegistry().bind("jsonFileFilter", new JsonFileFilter());

            // then you can start the context
            context.start();

            Thread.sleep(5000);
            context.stop();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
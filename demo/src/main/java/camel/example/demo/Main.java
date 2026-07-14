package camel.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;


public class Main {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);

	public static void main(String[] args){

		// create a camel context
		// add our route to the context
		// start the route and let it do its work

		// The follwing is a standalone example of how to run a Camel route without Spring Boot. 
		// It creates a CamelContext, adds the ExampleRoute to it, starts the context, 
		// and then stops it after a short delay.
		
		try(CamelContext context = new DefaultCamelContext()) {
			context.addRoutes(new camel.example.demo.route.ExampleRoute());
			context.start();
			Thread.sleep(2000);
			context.stop();
		} catch (Exception e) {
			logger.error("Error starting Camel context", e);
		}

		System.out.println("Hello World!");
	}

}

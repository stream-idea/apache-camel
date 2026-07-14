
package camel.example.demo.route;

import org.apache.camel.builder.RouteBuilder;

public class ExampleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:src/main/resources/files?noop=true") // Read files from the input directory without deleting them https://camel.apache.org/components/4.18.x/file-component.html#_endpoint_query_option_noop
        .log("Message: ${body}, Headers: ${headers}")
        .to("file:src/main/resources/files/output")
        .log("Hello from Camel route!");
    }


}

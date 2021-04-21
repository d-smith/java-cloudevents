package demo;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;


import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Main {
    public static void main(String... args) throws Exception {

        CloudEvent e1 = CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withSource(URI.create("ab/c/d"))
                .withData("text/plain","This is a test".getBytes())
                .withType("e1")
                .withTime(OffsetDateTime.now())
                .build();



        System.out.println(e1);



    }


}
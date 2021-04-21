package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import static io.cloudevents.core.CloudEventUtils.mapData;



import java.net.URI;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Main {
    public static void main(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Foo foo = new Foo("something");

        byte[] data = mapper.writeValueAsBytes(foo);

        CloudEvent e1 = CloudEventBuilder.v1()
                .withId(UUID.randomUUID().toString())
                .withSource(URI.create("ab/c/d"))
                .withData("application/json",data)
                .withType("e1")
                .withTime(OffsetDateTime.now())
                .build();



        System.out.println(e1.toString());



        String json = mapper.writeValueAsString(e1);
        System.out.println(json);



        PojoCloudEventData<Foo> cloudEventData = mapData(
                e1,
                PojoCloudEventDataMapper.from(mapper,Foo.class)
        );

        System.out.println(cloudEventData.getValue());

        String jsonToo = "{\"data\":{\"foo\":true},\"id\":\"9457d4cc-0842-402c-ac38-fc2c2c65f72e\",\"source\":\"ab/c/d\",\"type\":\"e1\",\"subject\":null,\"time\":{\"offset\":{\"totalSeconds\":-25200,\"id\":\"-07:00\",\"rules\":{\"transitions\":[],\"transitionRules\":[],\"fixedOffset\":true}},\"month\":\"APRIL\",\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":111,\"year\":2021,\"monthValue\":4,\"dayOfMonth\":21,\"hour\":15,\"minute\":16,\"second\":15,\"nano\":629956000},\"specVersion\":\"V1\",\"dataContentType\":\"application/json\",\"dataSchema\":null,\"extensionNames\":[],\"attributeNames\":[\"datacontenttype\",\"specversion\",\"id\",\"source\",\"time\",\"type\"]}";

        HashMap bar = new ObjectMapper().readValue(jsonToo, HashMap.class);
        System.out.println(bar);
        System.out.println(bar.get("type"));
        System.out.println(bar.get("data"));
    }


}
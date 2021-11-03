package org.acme.mqtt2kafka;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

public class MqttToKafka extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("paho:{{meters.mqtt.topic}}?brokerUrl={{mqtt.broker.url}}")
                .log("Message received: ${body}")
                .setHeader(KafkaConstants.KEY).expression(jsonpath("$.id"))
                .to("kafka:{{meters.kafka.topic}}?clientId=mqtt2kafka");
    }
}
package com.ecommerce.account.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Component
public class AccountConsumer implements Runnable{

    private  static final String ACCOUNT_TOPIC = "cwymk76o-inventory";
    private final KafkaConsumer kafkaConsumer;

    @Override
    public void run(){
        try{
            while (true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
                for (ConsumerRecord record : consumerRecords) {
                    System.out.println(record);
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public AccountConsumer(){

        String BROKERS  = "moped-01.srvs.cloudkafka.com:9094,moped-02.srvs.cloudkafka.com:9094,moped-03.srvs.cloudkafka.com:9094";
        String USERNAME = "cwymk76o";
        String PASSWORD = "x15MUph5C47AxIJ4lkJO-P0MTYEi1Eq9";
        String GROUP_ID = "Account-Consumer";
        String JAASTEMPLATE = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String JAASCFG = String.format(JAASTEMPLATE, USERNAME, PASSWORD);


        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", JAASCFG);

        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Arrays.asList(ACCOUNT_TOPIC));

        Thread t = new Thread(this);
        t.start();
    }
}

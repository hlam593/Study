package org.hl.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 消费者
 * @author hlam
 * @date 2022/12/3
 */
public class Consumer {

    public static void main(String[] args) {
        // 0 配置
        Properties properties = new Properties();
        // 0.1 连接集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.107.52.193:9092");
        // 0.2 反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 0.3 指定消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 1 创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        // 2 指定消费的主题
        List<String> list = new ArrayList();
        list.add("first");
        consumer.subscribe(list);
//        // 指定消费分区
//        List<TopicPartition> topicPartitionList = new ArrayList<>();
//        list.add(new TopicPartition("first", 1));

        // 3 消费
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record);
        }
        // 4 关闭
        consumer.close();
    }

}

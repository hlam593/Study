package org.hl.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author hlam
 * @date 2022/11/13
 */
public class Producer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // 1 连接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.107.52.193:9092");
        // 2 序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "org.hl.producer.CustomPartitioner");
        // 3 创建kafka生产对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        // 4 发送数据
        producer.send(new ProducerRecord<String, String>("first", "hello"));
        // 5 关闭资源
        producer.close();
    }


}

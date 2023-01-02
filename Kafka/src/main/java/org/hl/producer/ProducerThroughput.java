package org.hl.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 提高吞吐量
 * @author hlam
 * @date 2022/11/13
 */
public class ProducerThroughput {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // 1 连接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.107.52.193:9092");
        // 2 序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 批次大小，batch.size：默认16k
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 等待时间，linger.ms：默认0ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 压缩数据，none、gzip、snappy、lz4 和 zstd
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        // 缓冲区大小，RecordAccumulator：默认32m
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        // 3 创建kafka生产对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        // 4 发送数据
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<String, String>("first", "hello" + i));
        }
        // 5 关闭资源
        producer.close();
    }
}

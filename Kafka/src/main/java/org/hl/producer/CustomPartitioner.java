package org.hl.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区器
 * 1.实现 Partitioner 接口
 * 2.重写 partition
 * 3.返回分区号
 * @author hlam
 * @date 2022/11/19
 */
public class CustomPartitioner implements Partitioner {

    /**
     * @param s         主题
     * @param o         消息的KET
     * @param bytes     消息的KEY序列化后的字节数组
     * @param o1        消息的VALUE
     * @param bytes1    消息的VALUE序列化后的字节数组
     * @param cluster   集群元数据
     * @return          分区号
     */
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        String msg = o1.toString();
        // 如果消息包含hello，则发往0号分区，否则发往1号
        return msg.contains("hello") ? 0 : 1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

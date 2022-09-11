package org.hl.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;

/**
 * 通道初始化器
 * @author hlam
 * @date 2022/9/9
 */
public class HttpInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 先对请求解码  后对响应编码
        // pipeline.addLast(new HttpRequestDecoder());
        // pipeline.addLast(new HttpRequestEncoder());
        pipeline.addLast(new HttpServerCodec());
        // 压缩方式
        pipeline.addLast(new HttpContentCompressor());
        // 聚合  参数代表可以处理的最大值 此时是512kb
        pipeline.addLast(new HttpObjectAggregator(512 * 1024));
        // 处理器
        pipeline.addLast(new HttpHandler());
    }
}

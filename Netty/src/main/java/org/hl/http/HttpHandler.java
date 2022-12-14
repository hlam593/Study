package org.hl.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * http处理器
 * @author hlam
 * @date 2022/9/9
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        // DefaultFullHttpResponse是一个默认的完整http响应
        //    设定 版本、响应码、响应数据
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer("hello world!".getBytes())
        );
        // 设置响应头
        // 设置请求/响应头字段时， 可以使用HttpHeaderNames
        // 设置字段值时，可以使用 HttpHeaderValues
        // 设置包的大小时   调用readableBytes方法
        HttpHeaders headers = response.headers();
        headers.set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN + ";charset=UTF-8");
        headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}

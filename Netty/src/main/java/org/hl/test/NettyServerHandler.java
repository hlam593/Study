package org.hl.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 *
 * @author hlam
 * @date 2022/8/15
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 建立连接时的方法
     * @param ctx 通道处理器的上下文
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive done");
        ctx.writeAndFlush("welcome To Netty Server");
    }

    /**
     * 读取数据时的方法
     * @param ctx 通道处理器的上下文
     * @param msg 传送的消息数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ByteBuf是对ByteBuffer的封装
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Client Msg : " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Client Is From " + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完成触发的方法
     * @param ctx 通道处理器的上下文
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // Unpooled是提供在ByteBuf和string之间方便转换的工具类
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Client , How Do You Do?", CharsetUtil.UTF_8));
    }
}

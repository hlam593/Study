package org.hl.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author hlam
 * @date 2022/8/15
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive done");
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Server, I Am Client", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead done");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Server Msg :" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Server Address : " + ctx.channel().remoteAddress());
    }
}

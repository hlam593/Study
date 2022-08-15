package org.hl.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author hlam
 * @date 2022/8/15
 */
public class NettyServer {

    public static void main(String[] args) {

        // 创建两个reactor 构建主从模型
        // 管理channel 监听事件 无限循环的事件组（线程池）
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 服务端启动程序
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 设置相关参数
        serverBootstrap.group(bossGroup, workerGroup)
                // 声明使用的管道类型
                // netty                    nio                 bio
                // NioServerSocketChannel ServerSocketChannel serverSocket
                .channel(NioServerSocketChannel.class)
                // 设置前面通道的处理器  使用netty提供的日志打印处理器
                .handler(new LoggingHandler(LogLevel.INFO))
                // 定义客户端连接处理器的使用
                // ChannelInitializer 通道初始化器 要处理的是客户端通道  所以泛型设置为SocketChannel
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 通过channel 获取管道  pipeline
                        // 通道代表的是  连接的角色   管道代表的是  处理业务的逻辑管理
                        // 管道相当于链表 将不同的处理器连接起来  管理的是处理器的顺序
                        // 使用时常常使用尾插法   addLast 将处理器增加至尾部
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        System.out.println("服务器初始化完成");

        // 启动并设置端口号 异步启动
        try {
            ChannelFuture future = serverBootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}

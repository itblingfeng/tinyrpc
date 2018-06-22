package cn.blingfeng.netty.tinyrpc.server;

import cn.blingfeng.netty.tinyrpc.server.initialize.ServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class ServerLoader {
    private int port;

    public ServerLoader(int port) {
        this.port = port;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        final EventLoopGroup boss = new NioEventLoopGroup();
        final EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .localAddress("127.0.0.1", port)
                .childHandler(new ServerChannelInitializer());
        ChannelFuture f = bootstrap.bind();
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("server is bind port :" + port);
                } else {
                    System.out.println("server is start failed");
                    boss.shutdownGracefully();
                    worker.shutdownGracefully();
                }
            }
        });
    }
}

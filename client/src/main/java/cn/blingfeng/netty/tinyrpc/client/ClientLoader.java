package cn.blingfeng.netty.tinyrpc.client;

import cn.blingfeng.netty.tinyrpc.client.initialize.ClientChannelInitializer;
import cn.blingfeng.netty.tinyrpc.entity.RpcRequest;
import cn.blingfeng.netty.tinyrpc.entity.RpcRequestWrapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class ClientLoader {
    public static void main(String args[]) {

        new ClientLoader().start(new RpcRequestWrapper(new RpcRequest("a", "b", null), "127.0.0.1", 20707));
    }

    public void start(final RpcRequestWrapper requestWrapper) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress("127.0.0.1", 28080)
                .handler(new ClientChannelInitializer());
        ChannelFuture f = bootstrap.connect();
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture f) throws Exception {
                if (f.isSuccess()) {
                    System.out.println("->  CLIENT IS STARTED -v-");
                    f.channel().writeAndFlush(requestWrapper.getRequest());
                } else {
                    System.out.println("->  CLIENT IS START FAILED");
                    group.shutdownGracefully();
                }
            }
        });
    }
}

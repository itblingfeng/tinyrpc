package cn.blingfeng.netty.tinyrpc.client.initialize;

import cn.blingfeng.netty.tinyrpc.client.handler.ClientChannelHandler;
import cn.blingfeng.netty.tinyrpc.codec.TinyRpcCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new TinyRpcCodec())
                .addLast(new ClientChannelHandler());
    }
}

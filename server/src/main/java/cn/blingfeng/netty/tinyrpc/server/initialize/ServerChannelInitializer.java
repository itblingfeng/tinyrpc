package cn.blingfeng.netty.tinyrpc.server.initialize;

import cn.blingfeng.netty.tinyrpc.codec.TinyRpcCodec;
import cn.blingfeng.netty.tinyrpc.server.handler.RpcInvokeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new TinyRpcCodec())
                .addLast(new RpcInvokeHandler());
    }
}

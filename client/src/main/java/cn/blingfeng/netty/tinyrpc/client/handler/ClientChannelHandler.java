package cn.blingfeng.netty.tinyrpc.client.handler;

import cn.blingfeng.netty.tinyrpc.entity.RpcResponse;
import cn.blingfeng.netty.tinyrpc.util.RpcUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientChannelHandler extends SimpleChannelInboundHandler<RpcResponse> {

    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        System.out.println("客户端收到响应");
        RpcUtil.addInvokeResult(response.getId(), response.getResult());
        // 唤醒调用线程
        RpcUtil.notifyConsumer(response.getId());
    }

}

package cn.blingfeng.netty.tinyrpc.server.handler;

import cn.blingfeng.netty.tinyrpc.entity.Invoker;
import cn.blingfeng.netty.tinyrpc.entity.RpcRequest;
import cn.blingfeng.netty.tinyrpc.entity.RpcResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@ChannelHandler.Sharable
public class RpcInvokeHandler extends AbstractInvokeHandler {
    private static Map<String, Invoker<?>> cacheInvokes = new ConcurrentHashMap<String, Invoker<?>>();
    private static ReentrantLock LOCK = new ReentrantLock();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest req = (RpcRequest) msg;
        System.out.println("服务端收到请求");
        Invoker<?> invoker = cacheInvokes.get(req.getInterfaceName());
        if (invoker == null) {
            try {
                LOCK.lock();
                invoker = cacheInvokes.get(req.getInterfaceName());
                if (invoker == null) {
                    cacheInvokes.put(req.getInterfaceName(), createInvoker(req));
                    invoker = cacheInvokes.get(req.getInterfaceName());
                    if (invoker == null) throw new Exception("create invoker failed");
                }
            } finally {
                LOCK.unlock();
            }
        }
        ctx.writeAndFlush(new RpcResponse(req.getId(), null, invoker.invoke()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}

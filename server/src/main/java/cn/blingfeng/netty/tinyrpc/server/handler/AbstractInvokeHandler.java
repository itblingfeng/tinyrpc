package cn.blingfeng.netty.tinyrpc.server.handler;


import cn.blingfeng.netty.tinyrpc.entity.AbstractInvoker;
import cn.blingfeng.netty.tinyrpc.entity.Invoker;
import cn.blingfeng.netty.tinyrpc.entity.RpcRequest;
import cn.blingfeng.netty.tinyrpc.server.RefUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class AbstractInvokeHandler extends ChannelInboundHandlerAdapter {
    private static Objenesis objenesis = new ObjenesisStd(true);

    @SuppressWarnings({"unchecked"})
    <T> Invoker<T> createInvoker(final RpcRequest req) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class type = Class.forName(req.getInterfaceName());
        final Object proxy = RefUtil.getRef(req.getInterfaceName());
        return proxy == null ? null : new AbstractInvoker(proxy, type) {
            @Override
            public Object doInvoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                Method method = proxy.getClass().getMethod(req.getMethodName(), req.getParamsType());
                return method.invoke(proxy, req.getParamsVal());
            }
        };
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);
    }
}

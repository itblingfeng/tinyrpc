package cn.blingfeng.netty.tinyrpc.proxy;

import cn.blingfeng.netty.tinyrpc.client.ClientLoader;
import cn.blingfeng.netty.tinyrpc.entity.RpcRequest;
import cn.blingfeng.netty.tinyrpc.entity.RpcRequestWrapper;
import cn.blingfeng.netty.tinyrpc.util.RpcUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.concurrent.locks.LockSupport;

public class ProxyInvocationHandler implements InvocationHandler {
    private Class<?> type;

    public ProxyInvocationHandler(Class<?> type) {
        this.type = type;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            return proxy.equals(args);
        }
        if ("toString".equals(method.getName())) {
            return proxy.toString();
        }
        if ("hashCode".equals(method.getName())) {
            return proxy.hashCode();
        }
        RpcRequest request = new RpcRequest(type.getName(), method.getName(), args);
        RpcRequestWrapper requestWrapper = new RpcRequestWrapper(request, "127.0.0.1", 28080);
        RpcUtil.addWaiter(request.getId());
        new Thread(() -> {
            new ClientLoader().start(requestWrapper);
        }).start();
        LockSupport.park();
        return RpcUtil.getInvokeResult(request.getId());
    }
}

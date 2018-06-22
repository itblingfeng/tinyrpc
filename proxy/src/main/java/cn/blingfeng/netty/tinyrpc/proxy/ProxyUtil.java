package cn.blingfeng.netty.tinyrpc.proxy;

import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static <T> Object createProxy(Class type) {
        ProxyInvocationHandler handler = new ProxyInvocationHandler(type);
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, handler);
    }
}

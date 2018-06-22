package cn.blingfeng.netty.tinyrpc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    public static Object createProxy(InvocationHandler handler, Class type) {
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, handler);
    }
}

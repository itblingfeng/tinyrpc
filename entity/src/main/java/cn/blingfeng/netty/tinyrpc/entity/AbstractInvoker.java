package cn.blingfeng.netty.tinyrpc.entity;

import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unchecked")
public abstract class AbstractInvoker<T> implements Invoker<T> {
    private Class<?> type;
    private T proxy;
    private RpcRequest req;

    protected AbstractInvoker(T proxy, Class<?> type) {
        this.type = type;
        this.proxy = proxy;
    }


    public Object invoke() {
        try {
            return doInvoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Object doInvoke() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;


    public Class<?> getInterface() {
        return type;
    }

}

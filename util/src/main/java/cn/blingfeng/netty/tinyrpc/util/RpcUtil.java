package cn.blingfeng.netty.tinyrpc.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

public class RpcUtil {
    private final static Map<Long, Thread> waiterMap = new ConcurrentHashMap<>();
    private final static Map<Long, Object> invokeResult = new ConcurrentHashMap<>();

    public static Object getInvokeResult(long id) {
        return invokeResult.remove(id);
    }

    public static void notifyConsumer(long id) {
        LockSupport.unpark(waiterMap.remove(id));
    }

    public static void addWaiter(long id) {
        waiterMap.put(id, Thread.currentThread());
    }

    public static void addInvokeResult(long id, Object result) {
        invokeResult.put(id, result);
    }

}

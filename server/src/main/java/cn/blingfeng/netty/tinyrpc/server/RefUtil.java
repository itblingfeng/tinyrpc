package cn.blingfeng.netty.tinyrpc.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RefUtil {
    private static Map<String, Object> refCache = new ConcurrentHashMap<>();

    public static Object getRef(String name) {
        return refCache.get(name);
    }

    public static void putRef(String key, Object value) {
        refCache.put(key, value);
    }
}

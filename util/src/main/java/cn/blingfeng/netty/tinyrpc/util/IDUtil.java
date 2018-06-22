package cn.blingfeng.netty.tinyrpc.util;

import java.util.concurrent.atomic.AtomicLong;

public class IDUtil {
    private static AtomicLong id = new AtomicLong();

    public static long getId() {
        return id.getAndIncrement();
    }
}

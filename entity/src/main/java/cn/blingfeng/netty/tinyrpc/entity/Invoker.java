package cn.blingfeng.netty.tinyrpc.entity;

public interface Invoker<T> {

    Object invoke();

    Class<?> getInterface();
}

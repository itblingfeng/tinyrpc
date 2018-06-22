package cn.blingfeng.netty.tinyrpc.entity;

import lombok.Data;

@Data
public class RpcRequestWrapper {
    private RpcRequest request;

    private String address;

    private int port;

    public RpcRequestWrapper(RpcRequest request, String address, int port) {
        this.request = request;
        this.address = address;
        this.port = port;
    }
}

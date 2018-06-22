package cn.blingfeng.netty.tinyrpc.entity;


import lombok.Data;

@Data
public class RpcResponse {
    // rpc调用结果id
    private long id;
    // 错误信息
    private String errorMsg;
    // 调用返回结果
    private Object result;

    public RpcResponse(long id, String errorMsg, Object result) {
        this.id = id;
        this.errorMsg = errorMsg;
        this.result = result;
    }

    public static RpcResponse success(long id) {
        return new RpcResponse(id, null, null);
    }

    public static RpcResponse success(long id, Object result) {
        return new RpcResponse(id, null, result);
    }

    public static RpcResponse fail(long id, String errorMsg) {
        return new RpcResponse(id, errorMsg, null);
    }
}
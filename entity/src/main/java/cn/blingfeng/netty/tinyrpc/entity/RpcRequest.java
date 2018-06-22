package cn.blingfeng.netty.tinyrpc.entity;

import cn.blingfeng.netty.tinyrpc.util.IDUtil;
import lombok.Data;

@Data
public class RpcRequest {
    // rpc请求id
    private long id;
    // 请求的接口名称
    private String interfaceName;
    // 请求的方法名称
    private String methodName;
    // 请求的参数类型
    private Class<?>[] paramsType;
    // 请求的参数值
    private Object[] paramsVal;

    public RpcRequest(String interfaceName, String methodName, Object[] paramsVal) {
        this.id = IDUtil.getId();
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramsVal = paramsVal;
        if (paramsVal != null && paramsVal.length != 0) {
            paramsType = new Class<?>[paramsVal.length];
            for (int i = 0; i < paramsVal.length; i++) {
                paramsType[i] = paramsVal[i].getClass();
            }
        }
    }
}
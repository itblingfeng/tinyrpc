package cn.blingfeng.netty.tinyrpc.codec;

import cn.blingfeng.netty.tinyrpc.entity.RpcRequest;
import cn.blingfeng.netty.tinyrpc.entity.RpcResponse;
import cn.blingfeng.netty.tinyrpc.serialize.SerializeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * data type(0 req, 1 res)  data length      data
 * 1 byte              4 byte        infinity
 */
public class TinyRpcCodec extends ByteToMessageCodec<Object> {
    protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf buf) throws Exception {
        boolean isReq = true;
        if (obj instanceof RpcResponse)
            isReq = false;
        SerializeUtil.serialize(buf, obj, isReq);
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 1) {
            return;
        }
        boolean isReq = in.readByte() == 0 ? Boolean.TRUE : Boolean.FALSE;
        if (in.readableBytes() < 4) {
            return;
        }
        int len = in.readInt();
        byte[] body = new byte[len];
        in.readBytes(body);
        Object obj = SerializeUtil.deserialize(body, isReq);
        out.add(obj);
    }
}

package cn.blingfeng.netty.tinyrpc.serialize;

import cn.blingfeng.netty.tinyrpc.serialize.protostuff.ProtostuffSerialize;
import com.google.common.io.Closer;
import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SerializeUtil {
    // consider SPI
    private static Serialize serialize = new ProtostuffSerialize();
    private static Closer closer = Closer.create();

    public static void serialize(ByteBuf out, Object obj, boolean isReq) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            serialize.serialize(byteArrayOutputStream, obj);
            byte[] body = byteArrayOutputStream.toByteArray();
            int dataLength = body.length;
            out.writeByte(isReq ? 0 : 1);   // write msg type flag
            out.writeInt(dataLength);       // write msg content type
            out.writeBytes(body);           // write msg body
        } finally {
            closer.close();
        }
    }

    public static Object deserialize(byte[] body, boolean isReq) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
            closer.register(byteArrayInputStream);
            return serialize.deserialize(byteArrayInputStream, isReq);
        } finally {
            closer.close();
        }
    }
}

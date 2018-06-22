package cn.blingfeng.netty.tinyrpc.serialize;

import io.netty.buffer.ByteBuf;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author blingfeng
 * Serialize the object
 */
public interface Serialize {
    /**
     * Serialize the object
     *
     * @param out object outputStream
     * @param obj the object to be serialized
     */
    void serialize(OutputStream out, Object obj);

    /**
     * Deserialization
     * @param in the inputStream of the deserialized object
     * @param isReq is it a request？
     * @return deserialized objects
     */
    Object deserialize(InputStream in, boolean isReq);


}
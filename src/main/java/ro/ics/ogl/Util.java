package ro.ics.ogl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by mihais on 03.01.2016.
 */
public class Util {

    public static FloatBuffer toFloatBuffer(float[] v) {
        ByteBuffer buf = ByteBuffer.allocateDirect(v.length * 4);
        buf.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = buf.asFloatBuffer();
        buffer.put(v);
        buffer.position(0);
        return buffer;
    }


    public static FloatBuffer toFloatBuffer(float v) {
        ByteBuffer buf = ByteBuffer.allocateDirect(1 * 4);
        buf.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = buf.asFloatBuffer();
        buffer.put(v);
        buffer.position(0);
        return buffer;
    }
}

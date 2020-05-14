package com.ccssoft.cloudmessagemachine.mina.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @author moriarty
 * @date 2020/5/13 12:22
 */
public class TextEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        //字符编码器
        CharsetEncoder encoder = (CharsetEncoder) session.getAttribute("encoder");
        if (encoder == null) {
            encoder = Charset.forName( "GBK" ).newEncoder();
            session.setAttribute("encoder", encoder);
        }

        String value = (message == null ? "" : message.toString());
        IoBuffer buf = IoBuffer.allocate(value.length()) // 初始化buff size为消息长度
                .setAutoExpand(true); //可自动扩展buff size
        buf.putString(value, encoder); //把消息添加到buff，并使用指定编码器

        buf.flip(); // update position
        out.write(buf);
    }

    @Override
    public void dispose(IoSession session) throws Exception {
    }
}
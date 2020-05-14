package com.ccssoft.cloudmessagemachine.mina.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author moriarty
 * @date 2020/5/13 12:16
 */
public class TextCodecFactory implements ProtocolCodecFactory {

    private final TextEncoder encoder;
    private final TextDecoder decoder;

    public TextCodecFactory() {
        // 使用自定义编码/解码类
        encoder = new TextEncoder();
        decoder = new TextDecoder();
    }
    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }
    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}

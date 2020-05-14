package com.ccssoft.cloudmessagemachine.mina.config;

import com.ccssoft.cloudmessagemachine.mina.handler.MinaServerHandler;
import com.ccssoft.cloudmessagemachine.mina.socket.TextCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author moriarty
 * @date 2020/5/13 12:12
 */
@Configuration
public class MinaServerConfig {
    @Value("${mina-port}")
    private int port;
    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        final NioSocketAcceptor acceptor = new NioSocketAcceptor();
        // 消息的传送会经过一系列拦截器
        // 添加自定义的拦截器
        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextCodecFactory()));
//        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );

        // 设置会话管理类
        acceptor.setHandler(new MinaServerHandler());

        // 设置read buff size（字节）
        acceptor.getSessionConfig().setReadBufferSize( 2048); // buff是可自动扩展的，但设置一个合适值，效率会较高。
        // 设置空闲时间（单位：秒，会话空闲时会调用IoHandler的sessionIdle方法，BOTH_IDLE：读和写，READER_IDLE：读，WRITER_IDLE：写）
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        // 监听一个端口
        acceptor.bind(new InetSocketAddress(port));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // 发送一个广播，所以会话都能接受到
                acceptor.broadcast("Hello All.");
            }
        }, 10000);

        return acceptor;
    }
}

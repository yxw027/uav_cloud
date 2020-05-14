package com.ccssoft.cloudmessagemachine.mina.handler;

import com.ccssoft.cloudmessagemachine.mina.comon.ComonUtils;
import com.ccssoft.cloudmessagemachine.mina.iosession.IOSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author moriarty
 * @date 2020/5/13 12:13
 */
@Slf4j
public class MinaServerHandler extends IoHandlerAdapter {

    /**
     * 会话创建
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        // 获取客户端ip
        InetSocketAddress socketAddress = (InetSocketAddress) session.getRemoteAddress();
        InetAddress inetAddress = socketAddress.getAddress();
        log.info("sessionCreated  id=" + session.getId() + " , ip=" + inetAddress.getHostAddress());
    }

    /**
     * 会话打开
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        log.info("sessionOpened");
    }

    /**
     * 会话关闭
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        log.info("sessionClosed");
        IOSessionManager.removeSession(session);
    }

    /**
     * 会话等待
     * @param session
     * @param status
     * @throws Exception
     */


    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        log.info( "IDLE " + session.getIdleCount( status ));
    }

    /**
     * 会话异常
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        cause.printStackTrace();
    }

    /**
     * 接收消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // 读取客户端消息
        String str = message.toString();
        log.info("Message from session ["+session.getId()+"]: " + ComonUtils.toMessageWeNeed(str,session));
        // 给客户端返回数据
//        session.write();
    }

    /**
     * 发送消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        log.info("messageSent : " + message);
    }

    /**
     * 关闭输入流
     * @param session
     * @throws Exception
     */
    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
        log.info("inputClosed");
    }
}

package com.ccssoft.cloudmessagemachine.mina.comon;

import com.ccssoft.cloudmessagemachine.mina.iosession.IOSessionManager;
import com.ccssoft.cloudmessagemachine.redis.until.RedisUtil;
import org.apache.mina.core.session.IoSession;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * 处理数据工具类
 * @author moriarty
 * @date 2020/5/13 17:26
 */


public class ComonUtils {
    /**
     * 解析并处理数据
     */
    public static String toMessageWeNeed (String str, IoSession session) {
        String[] split = str.split(",");
        String head = split[0];
        String id = head.split("\\*")[1];
        IOSessionManager.addSession(Long.valueOf(id),session);
        String status = head.split("\\*")[3];
        if ("UD".equals(status)) {
            String t1 = split[1];
            String t2 = split[2];
            String data = t1.charAt(4)+""+t1.charAt(5)+"-"+t1.charAt(2)+t1.charAt(3)+"-"+t1.charAt(0)+t1.charAt(1);
            String s = "" + t2.charAt(0) + t2.charAt(1);
            String s1 = Integer.parseInt(s) + 8 + ":" + t2.charAt(2) + t2.charAt(3) +":"+ t2.charAt(4) + t2.charAt(5);
            Timestamp time = Timestamp.valueOf("20"+data+" "+s1);
//            String speed = split[8]+"";
//            String point = "["+split[4]+","+split[6]+"]";
//            boolean flag = false;
//            if (Double.valueOf(speed) > 4.0 && flag == false) {
//                redisUtil.set(id,point);
//                flag = true;
//            } else if (Double.valueOf(speed) > 4.0 && flag == true) {
//                redisUtil.append(id,":"+point);
//            } else {
//                flag = false;
//            }
            return "id:"+id+",status:"+split[3]+",point:["+split[4]+" "+split[6]+"],speed:"+split[8]+",direction:"+split[9]+",altitude:"+split[10]+",battery:"+split[13];
        } else if ("LK]".equals(status) || "LK".equals(status)) {
            session.write("[3G*"+id+"*0002*LK]");
            return "链路保持成功！";
        } else if ("RESET]".equals(status)) {
            return "盒子现在开始重启";
        }
        return "收到标示号异常，请检查！";
    }

    public static String restartBox (Long id) {
        IoSession session = IOSessionManager.getSession(id);
        session.write("[3G*"+id+"*0005*RESET]");
        return "开始重启。。。";
    }
}

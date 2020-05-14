package com.ccssoft.cloudcommon.common.utils.redis.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.text.CollatorUtilities;

import java.util.concurrent.TimeUnit;

/**
 * @author moriarty
 * @date 2020/4/27 17:13
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间（秒）
     * @return
     */
    public boolean expire (String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean append (String key,String value) {
        try{
            redisTemplate.opsForValue().append(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /***
     * 根据key获取过期时间
     * @param key 键 不能为null
     * @return 时间（秒） 返回0代表为永久有效
     */
    public long getExpir (String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return 存在的话返回true
     */
    public boolean hasKey (String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void del (String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get (String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set (String key,Object value) {
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 自增
     * @param key 键
     * @param delta 要增加几
     * @return
     */
    public long incr (String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }
}

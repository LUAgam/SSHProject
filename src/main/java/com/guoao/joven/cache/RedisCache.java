package com.guoao.joven.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 自定义cache,可以定义一个cache名称和一个缓存失效时间。时间到期会自动更新。在xml中配置。
 * <功能详细描述>
 * 
 * @author  lcma
 * @version  [版本号, 2016年9月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RedisCache implements Cache {
    /**
     * LOG日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);
    
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 缓存名称
     */
    private String name;
    
    /**
     * 缓存失效时间，时间到了会自动更新
     */
    private long liveTime = 0;
    
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
    
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }
    
    /**
     * 获取
     */
    @Override
    public ValueWrapper get(Object key) {
        final String keyf = (String)key;
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                throws DataAccessException {
                
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return toObject(value);
                
            }
        });
        return object != null ? new SimpleValueWrapper(object) : null;
    }
    
    /**
     * 新建
     */
    @Override
    public void put(Object key, Object value) {
        final String keyf = (String)key;
        final Object valuef = value;
        
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                throws DataAccessException {
                byte[] keyb = keyf.getBytes();
                byte[] valueb = toByteArray(valuef);
                connection.set(keyb, valueb);
                if (getLiveTime() > 0) {
                    connection.expire(keyb, getLiveTime());
                }
                return 1L;
            }
        });
    }
    
    /**
     * 删除
     */
    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
    
    /** 
     * 描述 : <Object转byte[]>. <br> 
     * <p> 
     * <使用方法说明> 
     * </p> 
     *  
     * @param obj 
     * @return 
     */
    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }
        return bytes;
    }
    
    /** 
     * 描述 : <byte[]转Object>. <br> 
     * <p> 
     * <使用方法说明> 
     * </p> 
     *  
     * @param bytes 
     * @return 
     */
    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(),ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.error(ex.getMessage(),ex);
        }
        return obj;
    }
    
    @Override
    public void evict(Object key) {
        final String keyf = (String)key;
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });
    }
    
    @Override
    public <T> T get(Object key, Class<T> type) {
        
        return null;
    }
    
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }
    
    public long getLiveTime() {
        return liveTime;
    }
    
    public void setLiveTime(long liveTime) {
        this.liveTime = liveTime;
    }

	@Override
	public <T> T get(Object arg0, Callable<T> arg1) {
		return null;
	}
    
}

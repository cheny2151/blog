package com.oc.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis客户端抽象类(实现类需要注入redisTemplate的实现类)
 *
 * @param <V>
 */
public abstract class AbstractRedisClient<V> implements RedisClient<V> {

    private RedisTemplate<String, V> redis;

    protected void setRedis(RedisTemplate<String, V> redis) {
        this.redis = redis;
    }

    private HashOperations<String, String, V> opsForHash;

    /**
     * 由于每次执行opsForHash()方法都会new一个，提取。
     */
    protected HashOperations<String, String, V> getHashOperation() {
        return opsForHash == null ? (opsForHash = redis.opsForHash()) : opsForHash;
    }

    //------------------------------ common ------------------------------

    @Override
    public void expire(String k, int days) {
        redis.expire(k, days, TimeUnit.DAYS);
    }

    @Override
    public boolean containsKey(String k) {
        return redis.hasKey(k);
    }

    @Override
    public void removeKey(String k) {
        redis.delete(k);
    }

    @Override
    public boolean exists(String k) {
        return redis.hasKey(k);
    }

    @Override
    public void delete(String k) {
        redis.delete(k);
    }

    @Override
    public long getExpire(String k, TimeUnit timeUnit) {
        return redis.getExpire(k, timeUnit);
    }

    //------------------------------ value ------------------------------

    @Override
    public void setValue(String k, V v, int days) {
        setValue(k, v);
        expire(k, days);
    }

    @Override
    public void setValue(String k, V v) {
        redis.opsForValue().set(k, v);
    }

    @Override
    public V getValue(String k) {
        return redis.opsForValue().get(k);
    }

    //------------------------------ list ------------------------------

    @Override
    public void addList(String k, List<V> values) {
        rightPushList(k, values);
    }

    @Override
    public void addList(String k, V v) {
        rightPush(k, v);
    }

    @Override
    public void rightPushList(String k, List<V> values) {
        redis.opsForList().rightPushAll(k, values);
    }

    @Override
    public void rightPush(String k, V v) {
        redis.opsForList().rightPush(k, v);
    }

    @Override
    public void leftPushList(String k, List<V> values) {
        redis.opsForList().leftPushAll(k, values);
    }

    @Override
    public void leftPush(String k, V v) {
        redis.opsForList().leftPush(k, v);
    }

    @Override
    public List<V> getList(String k) {
        List<V> range;
        return (range = redis.opsForList().range(k, 0, listSize(k) - 1)) == null || range.size() == 0 ? null : range;
    }

    @Override
    public V rightPop(String k) {
        return redis.opsForList().rightPop(k);
    }

    @Override
    public V leftPop(String k) {
        return redis.opsForList().leftPop(k);
    }

    @Override
    public Long listSize(String k) {
        return redis.opsForList().size(k);
    }

    //------------------------------ hash ------------------------------

    @Override
    public void HMSet(String k, Map<String, V> kv, int days) {
        HMSet(k, kv);
        expire(k, days);
    }

    @Override
    public void HMSet(String k, Map<String, V> kv) {
        getHashOperation().putAll(k, kv);
    }

    @Override
    public void HSet(String k, String hk, V v) {
        getHashOperation().put(k, hk, v);
    }

    @Override
    public V HGet(String k, String hk) {
        return getHashOperation().get(k, hk);
    }

    @Override
    public boolean HHasKey(String k, String hk) {
        return getHashOperation().hasKey(k, hk);
    }

    @Override
    public Map<String, V> HMGet(String k) {
        Map<String, V> map;
        return (map = getHashOperation().entries(k)) == null || map.size() == 0 ? null : map;
    }

    @Override
    public Set<String> HKeys(String k) {
        Set<String> keys;
        return (keys = getHashOperation().keys(k)) == null || keys.size() == 0 ? null : keys;
    }

    @Override
    public List<V> HValues(String k) {
        List<V> values;
        return (values = getHashOperation().values(k)) == null || values.size() == 0 ? null : values;
    }

    @Override
    public long HDel(String k, String hk) {
        return getHashOperation().delete(k, hk);
    }

}

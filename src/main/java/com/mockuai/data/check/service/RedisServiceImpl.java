package com.mockuai.data.check.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author : yangqi
 * @email : lukewei@mockuai.com
 * @description :
 * @since : 2020-08-16 16:51
 */
@Component
public class RedisServiceImpl implements RedisService {

    private RedisTemplate<String, Object> redisTemplate;

    private static final long ZERO = 0L;

    private static final boolean FALSE = false;

    @Override
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean expire(@NotNull String key, long time) {

        return Optional.ofNullable(redisTemplate.expire(key, time, TimeUnit.SECONDS)).orElse(FALSE);
    }

    @Override
    public long getExpire(@NotNull String key) {
        return Optional.ofNullable(redisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(ZERO);
    }

    @Override
    public boolean hasKey(@NotNull String key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(FALSE);
    }

    @Override
    public void del(@NotNull String... key) {

        redisTemplate.delete(Arrays.asList(key));
    }

    // ============================String=============================
    @Override
    public Object get(@NotNull String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(@NotNull String key, Object value) {
        redisTemplate.opsForValue().set(key, value);

    }

    @Override
    public void set(@NotNull String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    // ================================Map=================================
    @Override
    public Object hget(@NotNull String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    @Override
    public Map<Object, Object> hmget(@NotNull String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void hmset(@NotNull String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hmset(@NotNull String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
    }

    @Override
    public void hset(@NotNull String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    @Override
    public void hset(@NotNull String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    @Override
    public void hdel(@NotNull String key, Object... items) {
        redisTemplate.opsForHash().delete(key, items);
    }

    @Override
    public boolean hHasKey(@NotNull String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    @Override
    public double hincr(@NotNull String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    @Override
    public double hdecr(@NotNull String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================
    @Override
    public Set<Object> sGet(@NotNull String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public boolean sHasKey(@NotNull String key, Object value) {
        return Optional.ofNullable(redisTemplate.opsForSet().isMember(key, value)).orElse(FALSE);
    }

    @Override
    public long sSet(@NotNull String key, Object... values) {
        return Optional.ofNullable(redisTemplate.opsForSet().add(key, values)).orElse(ZERO);
    }

    @Override
    public long sSetAndTime(@NotNull String key, long time, Object... values) {
        long count = Optional.ofNullable(redisTemplate.opsForSet().add(key, values)).orElse(ZERO);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    @Override
    public long sGetSetSize(@NotNull String key) {
        return Optional.ofNullable(redisTemplate.opsForSet().size(key)).orElse(ZERO);
    }

    @Override
    public long setRemove(@NotNull String key, Object... values) {
        return Optional.ofNullable(redisTemplate.opsForSet().remove(key, values)).orElse(ZERO);
    }

    // ===============================list=================================

    @Override
    public List<Object> lGet(@NotNull String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public long lGetListSize(@NotNull String key) {
        return Optional.ofNullable(redisTemplate.opsForList().size(key)).orElse(ZERO);
    }

    @Override
    public Object lGetIndex(@NotNull String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void lSet(@NotNull String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public void lSet(@NotNull String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    @Override
    public Long lSet(@NotNull String key, List<Object> value) {

        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    @Override
    public void lSet(@NotNull String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    @Override
    public void lUpdateIndex(@NotNull String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Override
    public long lRemove(@NotNull String key, long count, Object value) {
        return Optional.ofNullable(redisTemplate.opsForList().remove(key, count, value)).orElse(ZERO);
    }
}

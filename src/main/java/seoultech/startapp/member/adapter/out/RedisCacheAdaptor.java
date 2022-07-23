package seoultech.startapp.member.adapter.out;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.out.RedisCachePort;

@Component
@RequiredArgsConstructor
public class RedisCacheAdaptor implements RedisCachePort {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public void setStringWithDayTTL(String key, String value, Integer ttl) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    valueOperations.set(key, value);
    stringRedisTemplate.expire(key, ttl, TimeUnit.DAYS);
  }

  @Override
  public String findByKey(String key) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    return valueOperations.get(key);
  }

  @Override
  public Boolean deleteByKey(String key) {
    return stringRedisTemplate.delete(key);
  }
}

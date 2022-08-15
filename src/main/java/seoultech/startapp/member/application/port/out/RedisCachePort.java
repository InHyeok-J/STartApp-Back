package seoultech.startapp.member.application.port.out;

import java.util.concurrent.TimeUnit;

public interface RedisCachePort {
  void setStringWithTTL(String key, String value, Integer ttl, TimeUnit timeUnit);
  String findByKey(String key);
  Boolean deleteByKey(String key);
}

package seoultech.startapp.member.application.port.out;

public interface RedisCachePort {
  void setStringWithDayTTL(String key, String value, Integer ttl);
  String findByKey(String key);
  Boolean deleteByKey(String key);
}

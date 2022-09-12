package seoultech.startapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/api/health")
  public String healthCheck() {
    return "ok";
  }

  @GetMapping("/api/version")
  public String checkVersion(){
    return "v1";
  }
}

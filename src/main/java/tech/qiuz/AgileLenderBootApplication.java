package tech.qiuz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhuihui.qiu
 */

@SpringBootApplication
@EnableRedisHttpSession
public class AgileLenderBootApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(AgileLenderBootApplication.class, args);
  }

}
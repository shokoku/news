package kr.sanus.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewsApplication {

  public static void main(String[] args) {
    SpringApplication.run(NewsApplication.class, args);
  }
}

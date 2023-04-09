
package org.phoenix.demo;

import org.phoenix.demo.security.service.SimpleUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class DataBootstrap implements CommandLineRunner {

  private final SimpleUserService simpleUserService;

  public DataBootstrap(SimpleUserService simpleUserService) {
    this.simpleUserService = simpleUserService;
  }


  @Override
  public void run(String... strings) throws Exception {

   //simpleUserService.saveUser();


  }
}
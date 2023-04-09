package org.phoenix.demo.core.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class SimpleEmployeeController {



  @GetMapping
  public String test(){
    return "Sefa Cihangir";
  }

}

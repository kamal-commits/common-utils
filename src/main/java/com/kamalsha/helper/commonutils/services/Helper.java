package com.kamalsha.helper.commonutils.services;

import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Helper {

  public void print() {
    log.info("Hello from Helper");
  }

}

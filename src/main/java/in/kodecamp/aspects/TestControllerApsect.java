package in.kodecamp.aspects;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

@Aspect
public class TestControllerApsect {

  @Around("@annotation(EnabledException)")
  public ResponseEntity<?> handleException() {
    return null;
  }


}

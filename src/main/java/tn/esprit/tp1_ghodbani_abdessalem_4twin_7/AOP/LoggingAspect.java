package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before(value = "execution (* tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services..*(..))")
    public void logMethodeEntry(JoinPoint joinPoint){
    String name=joinPoint.getSignature().getName();
    log.info("IN Methode "+name);
    System.out.println("IN Methode "+name);
    }

    @After(value = "execution (* tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services..*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("« Out of method " + name + " : ");
        System.out.println("« Out of method " + name + " : ");
    }

}

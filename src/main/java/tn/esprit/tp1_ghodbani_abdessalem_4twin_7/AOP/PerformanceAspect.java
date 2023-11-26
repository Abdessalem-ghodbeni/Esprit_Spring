package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class PerformanceAspect {

    @Around(value = "execution(* tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services..*(..))")
    public Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime
                +"milliseconds.");
        return obj;
    }
}

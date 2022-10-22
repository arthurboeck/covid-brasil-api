package br.com.covid.brazil.api.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class JoinPointAfterThrowingAspect {

    private static final Logger log = Logger.getLogger(JoinPointAfterThrowingAspect.class.getName());

    @Pointcut("execution(* br.com.covid.brazil.api.service.CovidDataService.getCovidDataById(..))")
    public void covidDataByIdPointcut() {
        // Do nothing
    }

    @AfterThrowing(
            pointcut = "covidDataByIdPointcut()",
            throwing = "e"
    )
    public void logExceptions(JoinPoint jp, Exception e) {
        log.severe(e.getMessage());
    }
}

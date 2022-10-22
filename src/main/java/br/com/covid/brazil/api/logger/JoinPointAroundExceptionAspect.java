package br.com.covid.brazil.api.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class JoinPointAroundExceptionAspect {

    private static final Logger log = Logger.getLogger(JoinPointAroundExceptionAspect.class.getName());

    @Pointcut("execution(* br.com.covid.brazil.api.service.CovidDataService.getCovidDataById(..))")
    public void covidDataByIdPointcut() {
        // Do nothing
    }

    @Around("covidDataByIdPointcut()")
    public Object aroundAdviceException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            log.severe(e.getMessage());
            log.info("Retrying operation");
            return pjp.proceed(pjp.getArgs());
        }
    }
}

package br.com.covid.brazil.api.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AroundAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* br.com.covid.brazil.api.service.CovidDataService.getAllCovidData(..))")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Arguments passed to method are: {}", Arrays.toString(joinPoint.getArgs()));
        final Object result = joinPoint.proceed();
        logger.info("Result from method is: {}", result);
        return result;
    }
}

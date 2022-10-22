package br.com.covid.brazil.api.logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterReturnAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* br.com.covid.brazil.api.service.CovidDataService.getAllCovidData(..))")
    public void allCovidDataPointcut() {
        // Do nothing
    }

    @AfterReturning(pointcut = "allCovidDataPointcut()", returning = "returnValue")
    public void afterReturn(final Object returnValue) throws Throwable {
        logger.info("value return was {}", returnValue);
    }
}

package br.com.covid.brazil.api.logger;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* br.com.covid.brazil.api.service.CovidDataService.getAllCovidData(..))")
    public void allCovidDataPointcut() {
        // Do nothing
    }

    @AfterThrowing(pointcut = "allCovidDataPointcut()", throwing = "exception")
    public void afterThrow(final Exception exception) throws Throwable {
        logger.info("Exception thrown was {}", exception.getMessage());
    }
}

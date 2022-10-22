package br.com.covid.brazil.api.logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* br.com.covid.brazil.api.service.CovidDataService.getAllCovidData(..))")
    public void afterAdvice() throws Throwable {
        logger.info("I'm done calling the method");
    }
}

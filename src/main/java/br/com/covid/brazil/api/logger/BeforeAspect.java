package br.com.covid.brazil.api.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* br.com.covid.brazil.api.service.CovidDataService.getAllCovidData(..))")
    public void beforeAdvice() throws Throwable {
        logger.info("I would be executed just before method starts");
    }
}

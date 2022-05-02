package dev.trion.training.app;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleAppApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SampleAppApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder)
    {
        return restTemplateBuilder.build();
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry)
    {
        return new TimedAspect(registry);
    }
}

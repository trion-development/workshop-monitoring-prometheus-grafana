package dev.trion.training.app.web;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/shop")
public class ShopController
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<Integer, Integer> products =  Map.of(0, 25, 1, 50, 2, 75);

    private final MeterRegistry meterRegistry;
    private final DistributionSummary distributionSummary;

    public ShopController(MeterRegistry meterRegistry)
    {
        this.meterRegistry = meterRegistry;

        distributionSummary = DistributionSummary.builder("app_product_prizes")
           .baseUnit("euro")
           .description("Prize of bought product")
           .publishPercentiles(0.5, 0.95, 0.99) // prometheus summary
           .minimumExpectedValue(Double.valueOf(products.values().stream().min(Integer::compareTo).orElse(0)))
           .maximumExpectedValue(Double.valueOf(products.values().stream().max(Integer::compareTo).orElse(100)))
           .publishPercentileHistogram()  // prometheus histogram (calc. range)
           .serviceLevelObjectives(1, 25, 50, 75, 100) // prometheus histogram (specified range)
           .register(meterRegistry);
    }

    @GetMapping
    public Integer buy()
    {
        var value = products.get(new Random().nextInt(3));
        logger.info("Sold product {}", value);
        distributionSummary.record(value);

        return value;
    }
}

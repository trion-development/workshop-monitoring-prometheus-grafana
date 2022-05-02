package dev.trion.training.app.web;

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

    @GetMapping
    public Integer buy()
    {
        var value = products.get(new Random().nextInt(3));
        logger.info("Sold product {}", value);

        return value;
    }
}

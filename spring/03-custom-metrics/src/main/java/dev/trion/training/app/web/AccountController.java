package dev.trion.training.app.web;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/account")
public class AccountController
{
    final String success = "SUCCESS";
    final String duplicate = "DUPLICATE_USER";

    private final MeterRegistry meterRegistry;

    private final Counter successCounter;
    private final Counter failCounter;

    public AccountController(MeterRegistry meterRegistry)
    {
        this.meterRegistry = meterRegistry;
        successCounter = meterRegistry.counter("app_account_registrations", "result", "success");

        failCounter = Counter.builder("app_account_registrations")
           .description("Number of account registrations")
           .tag("result", "fail")
           .register(meterRegistry);
    }

    @GetMapping("/register")
    public String register(String username)
    {
        if ( new Random().nextInt(100) < 20)
        {
            throw new DuplicateUserException();
        }
        successCounter.increment();
        return success;
    }

    @ExceptionHandler
    public String handleDuplicate(HttpServletRequest r, DuplicateUserException e)
    {
        // r.setAttribute(RequestDispatcher.ERROR_EXCEPTION, e);
        failCounter.increment();
        return duplicate;
    }
}

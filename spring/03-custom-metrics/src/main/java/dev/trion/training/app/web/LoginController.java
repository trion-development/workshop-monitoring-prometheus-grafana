package dev.trion.training.app.web;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController
{
    private final MeterRegistry meterRegistry;
    private final LoginService loginService;

    public LoginController(MeterRegistry meterRegistry, LoginService loginService)
    {
        this.meterRegistry = meterRegistry;
        this.loginService = loginService;
    }

    @GetMapping
    public void login()
    {
        meterRegistry.timer("app_timer_logins").record(() -> { // programmatic api
            loginService.doLogin();
        });
    }
}


@Service
class LoginService
{
    @Timed(value = "app_timed_logins") // annotation API
    public void doLogin()
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

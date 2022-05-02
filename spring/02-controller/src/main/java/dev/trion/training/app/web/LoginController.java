package dev.trion.training.app.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController
{
    @GetMapping
    public void login() throws InterruptedException
    {
        TimeUnit.MILLISECONDS.sleep( new Random().nextInt(1000) );
    }
}

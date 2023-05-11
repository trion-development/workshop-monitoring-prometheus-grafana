package dev.trion.training.app.web;

import jakarta.servlet.RequestDispatcher;
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

    @GetMapping("/register")
    public String register(String username)
    {
        if ( new Random().nextInt(100) < 20)
        {
            throw new DuplicateUserException();
        }
        return success;
    }

    @ExceptionHandler
    public String handleDuplicate(HttpServletRequest r, DuplicateUserException e)
    {
        //r.setAttribute(RequestDispatcher.ERROR_EXCEPTION, e);
        return duplicate;
    }

}

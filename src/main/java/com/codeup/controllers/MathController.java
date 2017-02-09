package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by user on 2/7/17.
 */
@Controller
public class MathController {
    @GetMapping("/add/{firstDigit}/and/{secondDigit}")
    @ResponseBody
    public String addition(@PathVariable int firstDigit, @PathVariable int secondDigit) {
        int sum = firstDigit + secondDigit;
        return "<h1>The sum of " + firstDigit + " and " + secondDigit + " is: " + sum + ". </h1>";
    }

    @GetMapping("/subtract/{firstDigit}/from/{secondDigit}")
    @ResponseBody
    public String subtract(@PathVariable int firstDigit, @PathVariable int secondDigit) {
        int difference = secondDigit - firstDigit;
        return "<h1>The difference of " + firstDigit + " and " + secondDigit + " is: " + difference + ". </h1>";
    }

    @GetMapping("/multiply/{firstDigit}/and/{secondDigit}")
    @ResponseBody
    public String multiply(@PathVariable int firstDigit, @PathVariable int secondDigit) {
        int total = firstDigit * secondDigit;
        return "<h1>" + firstDigit + " times " + secondDigit + " is: " + total + ". </h1>";
    }

    @GetMapping("/divide/{firstDigit}/by/{secondDigit}")
    @ResponseBody
    public String divide(@PathVariable double firstDigit, @PathVariable double secondDigit) {
        double result = firstDigit/secondDigit;
        return "<h1>" + firstDigit + " divided by " + secondDigit + " is: " + result + ". </h1>";
    }
}

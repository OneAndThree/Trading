package com.citi.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginout() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/modal", method = RequestMethod.GET)
    public String modal() {
        return "modal";
    }

    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public String nav() {
        return "nav";
    }

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public String portfolio() {
        return "portfolio";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        return "order";
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public String getKLineByName(String symbol, ModelMap model) {
        model.addAttribute("symbol", symbol);
        return "quote";
    }
}
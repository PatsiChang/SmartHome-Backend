package com.patsi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("mainPage")
    public String openPage() {
        return "test";
    }
}

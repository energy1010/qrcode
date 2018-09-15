package com.goebuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {
	
	@RequestMapping(value = "/error/{code}")
    public String error(@PathVariable int code, Model model) {
        String pager = "/content/error-pager";
        switch (code) {
            case 404:
                model.addAttribute("code", 404);
                model.addAttribute("status", "404");
                model.addAttribute("messages","页面未找到");
                model.addAttribute("error","404");
                pager = "/content/error-pager";
                break;
            case 500:
                model.addAttribute("code", 500);
                model.addAttribute("status", "500");
                model.addAttribute("messages","服务器出错了！");
                model.addAttribute("error","500");
                pager = "/content/error-pager";
                break;
        }
        return pager;
    }
	

}

package com.goebuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义异常处理页面
 * @author Administrator
 *
 */
@Controller
public class ErrorController {
	
	@RequestMapping(value = "/error/{code}")
    public String error(@PathVariable int code, Model model) {
		String pager = "/error/404";
//		String pager = "/content/error-pager";
        switch (code) {
            case 404:
            	model.addAttribute("title", "404错误");
                model.addAttribute("code", 404);
                model.addAttribute("status", "404");
                model.addAttribute("messages","页面未找到");
                model.addAttribute("error","404");
                 pager = "/error/404";
//                pager = "/content/error-pager3";
                break;
            case 500:
            	model.addAttribute("title", "500错误");
                model.addAttribute("code", 500);
                model.addAttribute("status", "500");
                model.addAttribute("messages","服务器出错了！");
                model.addAttribute("error","500");
                pager = "/error/500";
//                pager = "/content/error-pager3";
                break;
        }
        return pager;
    }
	

}

package com.goebuy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置静态资源映射
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	
	 @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);
        return filter;
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        //registry.addResourceHandler("/error/**").addResourceLocations("classpath:/error/");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorPageInterceptor());
        //.addPathPatterns("/action/**", "/mine/**");默认所有
        super.addInterceptors(registry);
    }
    
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//            return container -> {
//                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
//                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
//                container.addErrorPages(error404Page, error500Page);
//            };
//    
//    }
}
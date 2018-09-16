package com.goebuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置加载类
 * @author Administrator
 *
 */
//@EnableJpaRepositories(basePackages={"com.goebuy.**.repository"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.goebuy.**.**"})
@EnableJpaRepositories(basePackages = {"com.goebuy.**.**"})
public class QrcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrcodeApplication.class, args);
	}
}

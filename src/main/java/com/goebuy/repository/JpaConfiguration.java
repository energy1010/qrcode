package com.goebuy.repository;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @description JPA基础配置类
*
*/
//@ImportResource({"classpath:spring-servlet.xml"})  
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@EnableJpaRepositories(basePackages={"com.goebuy.**.repository"})
@EntityScan(basePackages={"com.goebuy.entity"})
public class JpaConfiguration {
	@Bean
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new  PersistenceExceptionTranslationPostProcessor();
	}
	
//	@Bean 
//	org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean LocalContainerEntityManagerFactoryBean(){
//		return new LocalContainerEntityManagerFactoryBean();
//	}
}
package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean 
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	//配置静态资源的处理  使DispatcherServlet对静态资源的请求转发到Servlet容器默认的Servlet上，而不是使用DispatcherServlet本身来处理此类请求
	 @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		 configurer.enable();
    }
	 
  /*  @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css");
    	registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js");
    	registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img");
    }*/
}

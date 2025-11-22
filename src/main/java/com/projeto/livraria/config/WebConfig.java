package com.projeto.livraria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Autowired
    private CategoriaConverter categoriaConverter;
    
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(categoriaConverter);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/**")
        .addResourceLocations("file:///C:/livraria-uploads/img/");
    }
}

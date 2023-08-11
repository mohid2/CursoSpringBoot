package com.ejemplo.demopaginaweb.config;

import com.ejemplo.demopaginaweb.util.viewxml.ClienteList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
       Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
       jaxb2Marshaller.setClassesToBeBound(new Class[]{ClienteList.class});
       return jaxb2Marshaller;
    }
}

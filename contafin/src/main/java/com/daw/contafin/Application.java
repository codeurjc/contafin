package com.daw.contafin;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;



@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Module springDataPageModule() {
	    return new SimpleModule().addSerializer(Page.class, new JsonSerializer<Page>() {
	        @Override
	        public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	            gen.writeStartObject();
	            gen.writeNumberField("totalElements",value.getTotalElements());
	            gen.writeNumberField("totalPages", value.getTotalPages());
	            gen.writeNumberField("number", value.getNumber());
	            gen.writeNumberField("size", value.getSize());
	            gen.writeBooleanField("first", value.isFirst());
	            gen.writeBooleanField("last", value.isLast());
	            gen.writeFieldName("content");
	            serializers.defaultSerializeValue(value.getContent(),gen);
	            gen.writeEndObject();
	        }
	    });
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
						.allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.allowCredentials(true).maxAge(3600);
			}
		};
	}
}
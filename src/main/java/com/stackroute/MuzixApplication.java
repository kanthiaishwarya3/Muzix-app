package com.stackroute;

import com.stackroute.repository.TrackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("application.properties")
public class MuzixApplication
{
	public static void main(String[] args)
	{
		// Starting the spring boot
		SpringApplication.run(MuzixApplication.class, args);
	}

}


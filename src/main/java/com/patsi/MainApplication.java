package com.patsi;

import com.patsi.bean.Recipe;
import com.patsi.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);

	}

}

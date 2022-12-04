package com.example.study4test;

import com.example.study4test.entity.UserRole;
import com.example.study4test.entity.Users;
import com.example.study4test.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.User;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Arrays;


@SpringBootApplication
@EnableSwagger2
public class Study4TestApplication implements CommandLineRunner {
    @Autowired
    private  UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Study4TestApplication.class, args);
    }
    @Override
    public void run(String... params) throws Exception {
        Users admin = new Users();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_ADMIN)));

        userService.signup(admin);



        Users client = new Users();
        client.setUsername("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_CLIENT)));

        userService.signup(client);


    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.study4test")).build();
    }
}

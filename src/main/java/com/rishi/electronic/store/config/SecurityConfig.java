package com.rishi.electronic.store.config;

import com.rishi.electronic.store.entites.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    //SecurityFilterChain beans
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        //configurations
        //urls
        //public koun se protected
        //koun se url admin , knoun se normal user,

        //configuring url
       /* security.authorizeHttpRequests(request->{
            request.requestMatchers("/products").authenticated();
            request.anyRequest().permitAll();
                }
        );*/

        //Configuring urls
        //cors ko ham abhi ke lie disable ky hai
        //isko ham log baad mein sikhenge
        security.cors(AbstractHttpConfigurer::disable);
        // csrf ko ham abhi ke liye disable kiy hai
        // isko ham log badd mai sikhenge

        security.csrf(AbstractHttpConfigurer::disable);
        //csrf ko hame abhi ke lie disable
        security.cors(AbstractHttpConfigurer::disable);
        //configuring url

        security.authorizeHttpRequests(request->
                        request.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("ADMIN","NORMAL")
                                .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                                .requestMatchers("/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users").permitAll()
                                .requestMatchers(HttpMethod.GET,"/categories/**").permitAll()
                                .requestMatchers("/categories/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
        );
        //We can use Method way to secure authorize go to controller
          //form based login
        //kis type ki security hai
//        httpSecurity.formLogin(Customizer.withDefaults());
        security.httpBasic(Customizer.withDefaults());
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

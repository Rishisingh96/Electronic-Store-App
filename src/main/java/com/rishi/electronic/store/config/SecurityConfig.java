package com.rishi.electronic.store.config;

import com.rishi.electronic.store.security.JWTAuthenticationFilter;
import com.rishi.electronic.store.security.JwtAuthenticationEntryPoint;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JWTAuthenticationFilter filter;

    @Autowired
    JwtAuthenticationEntryPoint entryPoint;

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
//        security.cors(AbstractHttpConfigurer::disable);
        // csrf ko ham abhi ke liye disable kiy hai
        // isko ham log badd mai sikhenge

        security.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();

                        //arigins
                        //methods
//                        corsConfiguration.addAllowedOrigin("http://localhost:4200");
//                        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200","http://localhost:4300"));
                        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                        corsConfiguration.setAllowedMethods(List.of("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(List.of("*"));
                        corsConfiguration.setMaxAge(4000L);
                        return corsConfiguration;
                    }
                }));
        security.csrf(AbstractHttpConfigurer::disable);
        //csrf ko hame abhi ke lie disable
//        security.cors(AbstractHttpConfigurer::disable);
        //configuring url

        security.authorizeHttpRequests(request->
                        request.requestMatchers(HttpMethod.DELETE,"/users/**").hasRole(AppConstants.ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole(AppConstants.ROLE_ADMIN,AppConstants.ROLE_NORMAL)
                                .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                                .requestMatchers("/products/**").hasRole(AppConstants.ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users").permitAll()
                                .requestMatchers(HttpMethod.GET,"/categories/**").permitAll()
                                .requestMatchers("/categories/**").hasRole(AppConstants.ROLE_ADMIN)
                                .requestMatchers(HttpMethod.POST,"/auth/generate-token").permitAll()
                                .requestMatchers("/auth/**").authenticated()
                                .anyRequest().permitAll()
        );
        //We can use Method way to secure authorize go to controller
          //form based login
        //kis type ki security hai
//        httpSecurity.formLogin(Customizer.withDefaults());
//        security.httpBasic(Customizer.withDefaults());
        //entry point
        security.exceptionHandling(ex ->ex.authenticationEntryPoint(entryPoint));

        //session creation policy
        security.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//        main  -->


        security.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
